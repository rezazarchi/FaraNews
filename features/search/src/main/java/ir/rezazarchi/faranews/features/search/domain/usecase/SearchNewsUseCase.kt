package ir.rezazarchi.faranews.features.search.domain.usecase

import ir.rezazarchi.faranews.core.data.Error
import ir.rezazarchi.faranews.core.data.Result
import ir.rezazarchi.faranews.features.search.domain.model.SearchedNews
import ir.rezazarchi.faranews.features.search.domain.repo.SearchNewsRepository
import kotlinx.coroutines.flow.Flow

class SearchNewsUseCase(private val repository: SearchNewsRepository) {
    operator fun invoke(): Flow<Result<List<SearchedNews>, Error>> {
        return repository.searchNews()
    }
}