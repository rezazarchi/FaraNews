package ir.rezazarchi.metamovie.features.search.presentation.viewmode

import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val searchViewModelModule = module {
    viewModelOf(::SearchViewmodel)
}