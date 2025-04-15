package ir.rezazarchi.faranews.features.bookmark.di

import ir.rezazarchi.faranews.bookmark.di.bookmarkCommonModule
import ir.rezazarchi.faranews.features.bookmark.presentation.viewmode.BookmarkedViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val bookmarkModule = module {
    includes(bookmarkCommonModule)
    viewModelOf(::BookmarkedViewModel)
}