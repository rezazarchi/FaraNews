package ir.rezazarchi.metamovie.features.details.domain.repo

import ir.rezazarchi.metamovie.core.data.Error
import ir.rezazarchi.metamovie.core.data.Result
import ir.rezazarchi.metamovie.features.details.domain.model.MovieDetails
import kotlinx.coroutines.flow.Flow

interface MovieDetailsRepository {
    fun getMovieDetails(movieId: Long): Flow<Result<MovieDetails, Error>>
}