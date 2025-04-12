package ir.rezazarchi.faranews.features.details.data.repository

import ir.rezazarchi.faranews.core.data.Error
import ir.rezazarchi.faranews.core.data.Result
import ir.rezazarchi.faranews.database.dao.NewsDao
import ir.rezazarchi.faranews.features.details.data.mapper.NewsDetailsMapper.toNewsDetails
import ir.rezazarchi.faranews.features.details.data.remote.service.NewsDetailsApiService
import ir.rezazarchi.faranews.features.details.domain.model.NewsDetails
import ir.rezazarchi.faranews.features.details.domain.repo.NewsDetailsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class NewsDetailsRepositoryImplementation(
    val apiService: NewsDetailsApiService,
    val dao: NewsDao,
) : NewsDetailsRepository {
    override fun getNewsDetails(newsId: Long): Flow<Result<NewsDetails, Error>> {
        return dao.getNewsById(newsId).map {
            Result.Success(it.toNewsDetails())
        }
    }
}