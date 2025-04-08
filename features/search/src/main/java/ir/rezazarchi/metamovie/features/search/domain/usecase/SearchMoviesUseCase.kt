package ir.rezazarchi.metamovie.features.search.domain.usecase

import ir.rezazarchi.metamovie.core.data.Error
import ir.rezazarchi.metamovie.core.data.Result
import ir.rezazarchi.metamovie.features.search.domain.model.SearchedNews
import ir.rezazarchi.metamovie.features.search.domain.repo.SearchNewsRepository
import kotlinx.coroutines.flow.Flow

class SearchMoviesUseCase(private val repository: SearchNewsRepository) {
    operator fun invoke(query: String): Flow<Result<List<SearchedNews>, Error>> {
        return repository.searchNews()
    }
}