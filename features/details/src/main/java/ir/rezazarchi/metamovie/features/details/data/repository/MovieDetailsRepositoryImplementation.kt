package ir.rezazarchi.metamovie.features.details.data.repository

import ir.rezazarchi.metamovie.core.data.Error
import ir.rezazarchi.metamovie.core.data.Result
import ir.rezazarchi.metamovie.database.dao.NewsDao
import ir.rezazarchi.metamovie.features.details.data.mapper.MovieDetailsMapper.toMovieDetails
import ir.rezazarchi.metamovie.features.details.data.remote.service.MovieDetailsApiService
import ir.rezazarchi.metamovie.features.details.domain.model.NewsDetails
import ir.rezazarchi.metamovie.features.details.domain.repo.MovieDetailsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MovieDetailsRepositoryImplementation(
    val apiService: MovieDetailsApiService,
    val dao: NewsDao,
) : MovieDetailsRepository {
    override fun getMovieDetails(movieId: Long): Flow<Result<NewsDetails, Error>> {
        return dao.getNewsById(movieId).map {
            Result.Success(it.toMovieDetails())
        }
    }
}