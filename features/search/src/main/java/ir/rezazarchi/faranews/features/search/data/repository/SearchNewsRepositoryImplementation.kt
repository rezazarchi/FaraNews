package ir.rezazarchi.faranews.features.search.data.repository

import ir.rezazarchi.faranews.core.data.Result
import ir.rezazarchi.faranews.core.data.onError
import ir.rezazarchi.faranews.core.data.onSuccess
import ir.rezazarchi.faranews.core.utils.Constant.API_KEY
import ir.rezazarchi.faranews.core.utils.Constant.SEARCH_QUERIES
import ir.rezazarchi.faranews.core.utils.safeCall
import ir.rezazarchi.faranews.database.dao.NewsDao
import ir.rezazarchi.faranews.database.entity.NewsEntity
import ir.rezazarchi.faranews.features.search.data.remote.mapper.SearchedNewsMapper.toNewsEntity
import ir.rezazarchi.faranews.features.search.data.remote.mapper.SearchedNewsMapper.toSearchedNews
import ir.rezazarchi.faranews.features.search.data.remote.service.SearchNewsApiService
import ir.rezazarchi.faranews.features.search.domain.repo.SearchNewsRepository
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import java.time.LocalDate
import java.time.ZoneId
import java.time.ZonedDateTime

class SearchNewsRepositoryImplementation(
    private val apiService: SearchNewsApiService,
    private val dao: NewsDao,
) : SearchNewsRepository {

    override fun searchNews() = flow {

        val articlesList = arrayListOf<NewsEntity>()

        SEARCH_QUERIES.forEach { query ->
            safeCall {
                apiService.searchNews(
                    query = query,
                    from = LocalDate.now().minusDays(1).toString(),
                    sortBy = "publishedAt",
                    apiKey = API_KEY,
                )
            }.onSuccess {
                articlesList.addAll(it.toNewsEntity(query))
            }.onError {
                emit(Result.Error(it))
            }
            dao.insertNews(*articlesList.toTypedArray())
        }
        val now = ZonedDateTime.now(ZoneId.systemDefault())
        emitAll(
            dao.getYesterdayNews(
                now.minusDays(1).toLocalDate().atStartOfDay(now.zone).toInstant(),
                now.toLocalDate().atStartOfDay(now.zone).toInstant().minusNanos(1),
            ).map { newsEntities ->
                Result.Success(newsEntities.map { it.toSearchedNews() })
            })
    }
}