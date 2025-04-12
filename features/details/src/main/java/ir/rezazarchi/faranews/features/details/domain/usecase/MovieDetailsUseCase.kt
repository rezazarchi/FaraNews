package ir.rezazarchi.faranews.features.details.domain.usecase

import ir.rezazarchi.faranews.core.data.Error
import ir.rezazarchi.faranews.core.data.Result
import ir.rezazarchi.faranews.features.details.domain.model.NewsDetails
import ir.rezazarchi.faranews.features.details.domain.repo.MovieDetailsRepository
import kotlinx.coroutines.flow.Flow

class MovieDetailsUseCase(private val repository: MovieDetailsRepository) {
    operator fun invoke(movieId: Long): Flow<Result<NewsDetails, Error>> {
        return repository.getMovieDetails(movieId)
    }
}