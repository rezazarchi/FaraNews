package ir.rezazarchi.faranews.database.fake

import ir.rezazarchi.faranews.database.dao.NewsDao
import ir.rezazarchi.faranews.database.entity.NewsEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flowOf
import java.time.Instant

class FakeNewsDao : NewsDao {

    private val newsList = mutableListOf<NewsEntity>()
    private val newsFlow = MutableStateFlow<List<NewsEntity>>(emptyList())

    override fun getAllNews(): Flow<List<NewsEntity>> {
        return newsFlow
    }

    override fun getYesterdayNews(
        startOfYesterday: Instant,
        endOfYesterday: Instant
    ): Flow<List<NewsEntity>> {
        return newsFlow
    }

    override fun getNewsByTitle(title: String): Flow<List<NewsEntity>> {
        return flowOf(newsList.filter { it.title.contains(title) })
    }

    override fun getNewsById(newsId: Long): Flow<NewsEntity> {
        return flowOf(newsList.first { it.id == newsId })
    }

    override suspend fun insertNews(vararg news: NewsEntity) {
        newsList.removeAll { existing -> news.any { it.id == existing.id } }
        newsList.addAll(news)
        newsFlow.value = newsList.toList()
    }

}
