package ir.rezazarchi.faranews.bookmark.data.repository

import ir.rezazarchi.faranews.bookmark.data.mapper.BookmarkedNewsMapper.toBookmarkedNewsDetailed
import ir.rezazarchi.faranews.bookmark.domain.model.BookmarkedNews
import ir.rezazarchi.faranews.bookmark.domain.repo.BookmarkedNewsRepository
import ir.rezazarchi.faranews.core.data.Result
import ir.rezazarchi.faranews.database.dao.BookmarkedDao
import ir.rezazarchi.faranews.database.entity.BookmarkedNewsEntity
import kotlinx.coroutines.flow.map

class BookmarkedNewsRepositoryImplementation(
    val bookmarkedDao: BookmarkedDao,
) : BookmarkedNewsRepository {

    override fun getBookmarkedNews() =
        bookmarkedDao.getAllBookmarkedNews().map {
            Result.Success(it.map(BookmarkedNewsEntity::newsId).toSet())
        }

    override fun isBookmarked(newsId: Long) =
        bookmarkedDao.isBookmarked(newsId).map {
            Result.Success(it)
        }

    override suspend fun addBookmarkNews(news: BookmarkedNews) {
        bookmarkedDao.insertBookmarkedNews(BookmarkedNewsEntity(news))
    }

    override suspend fun removeBookmarkNews(news: BookmarkedNews) {
        bookmarkedDao.deleteBookmarkedNews(BookmarkedNewsEntity(news))
    }

    override fun getBookmarkedNewsDetailed() =
        bookmarkedDao.getAllBookmarkedNewsDetailed().map { newsEntities ->
            Result.Success(newsEntities.map { it.toBookmarkedNewsDetailed() })
        }

}