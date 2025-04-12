package ir.rezazarchi.faranews.database.fake

import ir.rezazarchi.faranews.database.dao.BookmarkedDao
import ir.rezazarchi.faranews.database.entity.BookmarkedNewsEntity
import ir.rezazarchi.faranews.database.entity.NewsEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flowOf

class FakeBookmarkedDao : BookmarkedDao {

    private val bookmarkedList = mutableListOf<BookmarkedNewsEntity>()
    private val bookmarkedFlow = MutableStateFlow<List<BookmarkedNewsEntity>>(emptyList())

    override fun getAllBookmarkedNews(): Flow<List<BookmarkedNewsEntity>> {
        return bookmarkedFlow
    }

    override fun isBookmarked(newsId: Long): Flow<Boolean> {
        return flowOf(bookmarkedList.any { it.newsId == newsId })
    }

    override suspend fun upsertBookmarkedNews(vararg bookmarkedNews: BookmarkedNewsEntity) {
        bookmarkedList.removeAll { existing -> bookmarkedNews.any { it.newsId == existing.newsId } }
        bookmarkedList.addAll(bookmarkedNews)
        bookmarkedFlow.value = bookmarkedList.toList()
    }

    override suspend fun insertBookmarkedNews(bookmarkedNews: BookmarkedNewsEntity) {
        if (!bookmarkedList.any { it.newsId == bookmarkedNews.newsId }) {
            bookmarkedList.add(bookmarkedNews)
            bookmarkedFlow.value = bookmarkedList.toList()
        }
    }

    override suspend fun deleteBookmarkedNews(bookmarkedNews: BookmarkedNewsEntity) {
        bookmarkedList.remove(bookmarkedNews)
        bookmarkedFlow.value = bookmarkedList.toList()
    }

    override fun getAllBookmarkedNewsDetailed(): Flow<List<NewsEntity>> {
        return flowOf(emptyList()) // This requires a reference to the news list to work properly
    }
}
