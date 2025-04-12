package ir.rezazarchi.faranews.database.fake

import ir.rezazarchi.faranews.database.dao.NewsDao
import ir.rezazarchi.faranews.database.entity.NewsEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flowOf
import java.time.Instant

class FakeNewsDao : NewsDao {

    private val movieList = mutableListOf<NewsEntity>()
    private val moviesFlow = MutableStateFlow<List<NewsEntity>>(emptyList())

    override fun getAllNews(): Flow<List<NewsEntity>> {
        return moviesFlow
    }

    override fun getYesterdayNews(
        startOfYesterday: Instant,
        endOfYesterday: Instant
    ): Flow<List<NewsEntity>> {
        return moviesFlow
    }

    override fun getNewsByTitle(title: String): Flow<List<NewsEntity>> {
        return flowOf(movieList.filter { it.title.contains(title) })
    }

    override fun getNewsById(newsId: Long): Flow<NewsEntity> {
        return flowOf(movieList.first { it.id == newsId })
    }

    override suspend fun insertNews(vararg news: NewsEntity) {
        movieList.removeAll { existing -> news.any { it.id == existing.id } }
        movieList.addAll(news)
        moviesFlow.value = movieList.toList()
    }

}
