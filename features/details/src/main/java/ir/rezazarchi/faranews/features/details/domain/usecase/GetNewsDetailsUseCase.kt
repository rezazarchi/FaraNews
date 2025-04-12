package ir.rezazarchi.faranews.features.details.domain.usecase

import ir.rezazarchi.faranews.core.data.Error
import ir.rezazarchi.faranews.core.data.Result
import ir.rezazarchi.faranews.features.details.domain.model.NewsDetails
import ir.rezazarchi.faranews.features.details.domain.repo.NewsDetailsRepository
import kotlinx.coroutines.flow.Flow

class GetNewsDetailsUseCase(private val repository: NewsDetailsRepository) {
    operator fun invoke(newsId: Long): Flow<Result<NewsDetails, Error>> {
        return repository.getNewsDetails(newsId)
    }
}