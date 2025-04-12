package ir.rezazarchi.faranews.features.details.domain.repo

import ir.rezazarchi.faranews.core.data.Error
import ir.rezazarchi.faranews.core.data.Result
import ir.rezazarchi.faranews.features.details.domain.model.NewsDetails
import kotlinx.coroutines.flow.Flow

interface NewsDetailsRepository {
    fun getNewsDetails(newsId: Long): Flow<Result<NewsDetails, Error>>
}