package ir.rezazarchi.metamovie.features.search.domain.usecase

import ir.rezazarchi.metamovie.core.data.Error
import ir.rezazarchi.metamovie.core.data.Result
import ir.rezazarchi.metamovie.features.search.domain.model.SearchedMovie
import ir.rezazarchi.metamovie.features.search.domain.repo.SearchMoviesRepository
import kotlinx.coroutines.flow.Flow

class SearchMoviesUseCase(private val repository: SearchMoviesRepository) {
    operator fun invoke(query: String): Flow<Result<List<SearchedMovie>, Error>> {
        return repository.searchMovie(query)
    }
}