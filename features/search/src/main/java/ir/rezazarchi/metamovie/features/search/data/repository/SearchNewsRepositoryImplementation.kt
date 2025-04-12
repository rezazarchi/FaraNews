package ir.rezazarchi.metamovie.features.search.data.repository

import ir.rezazarchi.metamovie.core.data.Result
import ir.rezazarchi.metamovie.core.data.onError
import ir.rezazarchi.metamovie.core.data.onSuccess
import ir.rezazarchi.metamovie.core.utils.Constant.API_KEY
import ir.rezazarchi.metamovie.core.utils.Constant.SEARCH_QUERIES
import ir.rezazarchi.metamovie.core.utils.safeCall
import ir.rezazarchi.metamovie.database.dao.NewsDao
import ir.rezazarchi.metamovie.database.entity.NewsEntity
import ir.rezazarchi.metamovie.features.search.data.remote.mapper.SearchedMoviesMapper.toNewsEntity
import ir.rezazarchi.metamovie.features.search.data.remote.mapper.SearchedMoviesMapper.toSearchedNews
import ir.rezazarchi.metamovie.features.search.data.remote.service.SearchNewsApiService
import ir.rezazarchi.metamovie.features.search.domain.repo.SearchNewsRepository
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
            ).map { movieEntities ->
                Result.Success(movieEntities.map { it.toSearchedNews() })
            })
    }
}