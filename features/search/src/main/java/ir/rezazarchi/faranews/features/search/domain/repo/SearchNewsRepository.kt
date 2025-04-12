package ir.rezazarchi.faranews.features.search.domain.repo

import ir.rezazarchi.faranews.core.data.Error
import ir.rezazarchi.faranews.core.data.Result
import ir.rezazarchi.faranews.features.search.domain.model.SearchedNews
import kotlinx.coroutines.flow.Flow

interface SearchNewsRepository {
    fun searchNews(): Flow<Result<List<SearchedNews>, Error>>
}