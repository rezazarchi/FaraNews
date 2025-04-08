package ir.rezazarchi.metamovie.features.search.domain.repo

import ir.rezazarchi.metamovie.core.data.Error
import ir.rezazarchi.metamovie.core.data.Result
import ir.rezazarchi.metamovie.features.search.domain.model.SearchedNews
import kotlinx.coroutines.flow.Flow

interface SearchNewsRepository {
    fun searchNews(): Flow<Result<List<SearchedNews>, Error>>
}