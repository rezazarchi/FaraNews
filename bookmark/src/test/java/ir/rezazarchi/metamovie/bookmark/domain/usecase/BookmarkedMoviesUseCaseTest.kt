package ir.rezazarchi.metamovie.bookmark.domain.usecase

import app.cash.turbine.test
import ir.rezazarchi.metamovie.bookmark.data.repository.BookmarkedMoviesRepositoryImplementation
import ir.rezazarchi.metamovie.bookmark.domain.repo.BookmarkedMoviesRepository
import ir.rezazarchi.metamovie.core.data.Result
import ir.rezazarchi.metamovie.database.dao.BookmarkedDao
import ir.rezazarchi.metamovie.database.fake.FakeBookmarkedDao
import ir.rezazarchi.metamovie.database.fake.FakeBookmarkedList
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class BookmarkedMoviesUseCaseTest {

    private lateinit var bookmarkedDao: BookmarkedDao
    private lateinit var repository: BookmarkedMoviesRepository
    private lateinit var useCase: BookmarkedMoviesUseCase

    @Before
    fun setUp() {
        bookmarkedDao = FakeBookmarkedDao()
        repository = BookmarkedMoviesRepositoryImplementation(
            bookmarkedDao = bookmarkedDao,
        )
        useCase = BookmarkedMoviesUseCase(repository)
    }

    @Test
    fun `insert bookmarks into database then check for useCase result`() = runBlocking {
        val bookmarkedMovie = FakeBookmarkedList.bookmarkedList.toTypedArray()
        bookmarkedDao.upsertBookmarkedMovie(*bookmarkedMovie)
        useCase().test {
            val flowResult = awaitItem()
            assert(flowResult is Result.Success)
            assert((flowResult as Result.Success).data.size == bookmarkedMovie.size)
        }
    }
}