package ir.rezazarchi.faranews.bookmark.domain.usecase

import app.cash.turbine.test
import ir.rezazarchi.faranews.bookmark.data.repository.BookmarkedNewsRepositoryImplementation
import ir.rezazarchi.faranews.bookmark.domain.repo.BookmarkedNewsRepository
import ir.rezazarchi.faranews.core.data.Result
import ir.rezazarchi.faranews.database.dao.BookmarkedDao
import ir.rezazarchi.faranews.database.fake.FakeBookmarkedDao
import ir.rezazarchi.faranews.database.fake.FakeBookmarkedList
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class BookmarkedNewsUseCaseTest {

    private lateinit var bookmarkedDao: BookmarkedDao
    private lateinit var repository: BookmarkedNewsRepository
    private lateinit var useCase: BookmarkedNewsUseCase

    @Before
    fun setUp() {
        bookmarkedDao = FakeBookmarkedDao()
        repository = BookmarkedNewsRepositoryImplementation(
            bookmarkedDao = bookmarkedDao,
        )
        useCase = BookmarkedNewsUseCase(repository)
    }

    @Test
    fun `insert bookmarks into database then check for useCase result`() = runBlocking {
        val bookmarkedMovie = FakeBookmarkedList.bookmarkedList.toTypedArray()
        bookmarkedDao.upsertBookmarkedNews(*bookmarkedMovie)
        useCase().test {
            val flowResult = awaitItem()
            assert(flowResult is Result.Success)
            assert((flowResult as Result.Success).data.size == bookmarkedMovie.size)
        }
    }
}