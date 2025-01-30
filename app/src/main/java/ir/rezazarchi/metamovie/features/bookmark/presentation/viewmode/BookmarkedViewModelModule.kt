package ir.rezazarchi.metamovie.features.bookmark.presentation.viewmode

import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val bookmarkedViewModelModule = module {
    viewModelOf(::BookmarkedViewModel)
}