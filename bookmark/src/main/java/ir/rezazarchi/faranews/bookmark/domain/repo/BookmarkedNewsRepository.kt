package ir.rezazarchi.faranews.bookmark.domain.repo

import ir.rezazarchi.faranews.core.data.Error
import ir.rezazarchi.faranews.core.data.Result
import ir.rezazarchi.faranews.bookmark.domain.model.BookmarkedNews
import ir.rezazarchi.faranews.bookmark.domain.model.BookmarkedNewsDetailed
import kotlinx.coroutines.flow.Flow

interface BookmarkedNewsRepository {
    fun getBookmarkedNews(): Flow<Result<Set<BookmarkedNews>, Error>>
    fun isBookmarked(newsId: Long): Flow<Result<Boolean, Error>>
    suspend fun addBookmarkNews(news: BookmarkedNews)
    suspend fun removeBookmarkNews(news: BookmarkedNews)
    fun getBookmarkedNewsDetailed(): Flow<Result<List<BookmarkedNewsDetailed>, Error>>
}