package ir.rezazarchi.faranews.features.search

import app.cash.turbine.test
import assertk.assertThat
import assertk.assertions.first
import assertk.assertions.isEmpty
import assertk.assertions.isEqualTo
import assertk.assertions.size
import io.mockk.coEvery
import io.mockk.mockk
import ir.rezazarchi.faranews.core.data.NetworkError
import ir.rezazarchi.faranews.core.data.Result
import ir.rezazarchi.faranews.core.utils.Constant
import ir.rezazarchi.faranews.database.dao.NewsDao
import ir.rezazarchi.faranews.database.fake.FakeNewsDao
import ir.rezazarchi.faranews.database.fake.FakeNewsList
import ir.rezazarchi.faranews.features.search.data.remote.fake.FakeSearchNewsResponseDto.fakeSearchDto
import ir.rezazarchi.faranews.features.search.data.remote.mapper.SearchedMoviesMapper.toSearchedMovie
import ir.rezazarchi.faranews.features.search.data.remote.mapper.SearchedMoviesMapper.toSearchedMovies
import ir.rezazarchi.faranews.features.search.data.remote.service.SearchNewsApiService
import ir.rezazarchi.faranews.features.search.data.repository.SearchNewsRepositoryImplementation
import ir.rezazarchi.faranews.features.search.domain.repo.SearchNewsRepository
import ir.rezazarchi.faranews.features.search.domain.usecase.SearchMoviesUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import retrofit2.Response

class SearchMoviesUseCaseTest {

    private lateinit var searchUseCase: SearchMoviesUseCase
    private lateinit var repository: SearchNewsRepository
    private lateinit var onlineApi: SearchNewsApiService
    private lateinit var cacheDb: NewsDao

    @Before
    fun setUp() {
        cacheDb = FakeNewsDao()
        onlineApi = mockk(relaxed = true)
        repository = SearchNewsRepositoryImplementation(onlineApi, cacheDb)
        searchUseCase = SearchMoviesUseCase(repository)
    }

    @Test
    fun `search from api failed while cache is empty`() = runBlocking {
        coEvery {
            onlineApi.searchNews("tag1", "testKey")
        } returns Response.error(500, mockk(relaxed = true))
        searchUseCase("tag1").test {
            val firstEmit = awaitItem()
            println("assert that first emission is error as type of unknown server error (500)")
            assert(firstEmit is Result.Error)
            assertThat((firstEmit as Result.Error).error).isEqualTo(NetworkError.UNKNOWN)
            val secondEmit = awaitItem()
            println("assert that second emission is success with empty list from the database")
            assert(secondEmit is Result.Success)
            assertThat((secondEmit as Result.Success).data).isEmpty()
            awaitComplete()
        }
    }

    @Test
    fun `search from api failed while we have cache in database`() = runBlocking {
        coEvery {
            onlineApi.searchNews("tag1", Constant.API_KEY)
        } returns Response.error(500, mockk(relaxed = true))
        val movies = FakeNewsList.newsList.toTypedArray()
        cacheDb.upsertMovies(*movies)
        searchUseCase("tag1").test {
            val firstEmit = awaitItem()
            println("assert that first emission is error as type of unknown server error (500)")
            assert(firstEmit is Result.Error)
            assertThat((firstEmit as Result.Error).error).isEqualTo(NetworkError.SERVER_ERROR)
            val secondEmit = awaitItem()
            println("assert that second emission is success with list of movies from the database")
            assert(secondEmit is Result.Success)
            assertThat((secondEmit as Result.Success).data).size().isEqualTo(movies.size)
            assertThat(secondEmit.data).first().isEqualTo(movies.first().toSearchedMovie())
            awaitComplete()
        }
    }

    @Test
    fun `search from api success then, inserted into database and, then get that list from database`() =
        runBlocking {
            coEvery {
                onlineApi.searchNews("movie1", Constant.API_KEY)
            } returns Response.success(fakeSearchDto)
            searchUseCase("movie1").test {
                val firstEmit = awaitItem()
                println("assert that first emission is success with 2 movies which have saved in database cache")
                assert(firstEmit is Result.Success)
                assertThat((firstEmit as Result.Success).data).size().isEqualTo(2)
                assertThat(firstEmit.data).first().isEqualTo(fakeSearchDto.toSearchedMovies().first())
                awaitComplete()
            }
        }
}