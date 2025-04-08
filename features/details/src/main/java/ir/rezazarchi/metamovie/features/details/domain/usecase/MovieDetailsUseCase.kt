package ir.rezazarchi.metamovie.features.details.domain.usecase

import ir.rezazarchi.metamovie.core.data.Error
import ir.rezazarchi.metamovie.core.data.Result
import ir.rezazarchi.metamovie.features.details.domain.model.NewsDetails
import ir.rezazarchi.metamovie.features.details.domain.repo.MovieDetailsRepository
import kotlinx.coroutines.flow.Flow

class MovieDetailsUseCase(private val repository: MovieDetailsRepository) {
    operator fun invoke(movieId: Long): Flow<Result<NewsDetails, Error>> {
        return repository.getMovieDetails(movieId)
    }
}