package ir.rezazarchi.metamovie.features.search.domain.repo

import ir.rezazarchi.metamovie.core.data.Error
import ir.rezazarchi.metamovie.core.data.Result
import ir.rezazarchi.metamovie.features.search.domain.model.SearchedMovie
import kotlinx.coroutines.flow.Flow

interface SearchMoviesRepository {
    fun searchMovie(query: String): Flow<Result<List<SearchedMovie>, Error>>
}