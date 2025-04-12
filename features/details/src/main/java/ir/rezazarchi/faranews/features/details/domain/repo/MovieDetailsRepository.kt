package ir.rezazarchi.faranews.features.details.domain.repo

import ir.rezazarchi.faranews.core.data.Error
import ir.rezazarchi.faranews.core.data.Result
import ir.rezazarchi.faranews.features.details.domain.model.NewsDetails
import kotlinx.coroutines.flow.Flow

interface MovieDetailsRepository {
    fun getMovieDetails(movieId: Long): Flow<Result<NewsDetails, Error>>
}