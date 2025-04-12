package ir.rezazarchi.faranews.features.details

import app.cash.turbine.test
import assertk.assertFailure
import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isInstanceOf
import io.mockk.mockk
import ir.rezazarchi.faranews.core.data.Result
import ir.rezazarchi.faranews.database.dao.NewsDao
import ir.rezazarchi.faranews.database.entity.NewsEntity
import ir.rezazarchi.faranews.database.entity.VideoStats
import ir.rezazarchi.faranews.database.fake.FakeNewsDao
import ir.rezazarchi.faranews.database.fake.FakeNewsList
import ir.rezazarchi.faranews.features.details.data.mapper.MovieDetailsMapper.toMovieDetails
import ir.rezazarchi.faranews.features.details.data.remote.service.MovieDetailsApiService
import ir.rezazarchi.faranews.features.details.data.repository.MovieDetailsRepositoryImplementation
import ir.rezazarchi.faranews.features.details.domain.repo.MovieDetailsRepository
import ir.rezazarchi.faranews.features.details.domain.usecase.MovieDetailsUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class NewsDetailsUseCaseTest {

    private lateinit var movieDetailsUseCase: MovieDetailsUseCase
    private lateinit var movieDetailsRepository: MovieDetailsRepository
    private lateinit var movieOnlineApi: MovieDetailsApiService
    private lateinit var movieLocalDatabase: NewsDao

    @Before
    fun setUp() = runBlocking {
        movieLocalDatabase = FakeNewsDao()
        movieLocalDatabase.upsertMovies(*FakeNewsList.newsList.toTypedArray())
        movieOnlineApi = mockk(relaxed = true)
        movieDetailsRepository =
            MovieDetailsRepositoryImplementation(movieOnlineApi, movieLocalDatabase)
        movieDetailsUseCase = MovieDetailsUseCase(movieDetailsRepository)

    }

    @Test
    fun `Get movie detail from database which exists`() = runBlocking {
        movieDetailsUseCase(1).test {
            val firstEmit = awaitItem()
            println("assert that first emission is success with the movie which have saved in database cache before")
            assert(firstEmit is Result.Success)
            assertThat((firstEmit as Result.Success).data).isEqualTo(
                NewsEntity(
                    id = 1,
                    tags = listOf("tag1", "tag2"),
                    thumbnailUrl = "",
                    videoUrl = "",
                    username = "Reza Z",
                    videoStats = VideoStats(0, 0, 0),
                ).toMovieDetails()
            )
            awaitComplete()
        }
    }

    @Test
    fun `Get movie detail from database which not exists`() {
        println("assert that this request will fail with NoSuchElementException")
        assertFailure {
            movieDetailsUseCase(98)
        }.isInstanceOf(NoSuchElementException::class)
    }

}