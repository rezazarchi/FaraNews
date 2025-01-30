package ir.rezazarchi.metamovie.features.search.data.repository

import ir.rezazarchi.metamovie.core.data.Result
import ir.rezazarchi.metamovie.core.data.onError
import ir.rezazarchi.metamovie.core.data.onSuccess
import ir.rezazarchi.metamovie.core.utils.Constant
import ir.rezazarchi.metamovie.core.utils.safeCall
import ir.rezazarchi.metamovie.database.dao.MoviesDao
import ir.rezazarchi.metamovie.features.search.data.remote.mapper.SearchedMoviesMapper.toMovieEntity
import ir.rezazarchi.metamovie.features.search.data.remote.mapper.SearchedMoviesMapper.toSearchedMovie
import ir.rezazarchi.metamovie.features.search.data.remote.service.SearchMovieApiService
import ir.rezazarchi.metamovie.features.search.domain.repo.SearchMoviesRepository
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class SearchMoviesRepositoryImplementation(
    val apiService: SearchMovieApiService,
    val dao: MoviesDao,
) : SearchMoviesRepository {

    override fun searchMovie(query: String) = flow {
        safeCall {
            apiService.searchMovies(query, Constant.API_KEY)
        }.onSuccess {
            dao.upsertMovies(*it.toMovieEntity().toTypedArray())
        }.onError {
            emit(Result.Error(it))
        }
        emitAll(dao.getMovieByTag(query).map { movieEntities ->
            Result.Success(movieEntities.map { it.toSearchedMovie() })
        })
    }
}