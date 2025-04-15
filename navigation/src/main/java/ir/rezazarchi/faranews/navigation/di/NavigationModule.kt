package ir.rezazarchi.faranews.navigation.di

import ir.rezazarchi.faranews.features.bookmark.di.bookmarkModule
import ir.rezazarchi.faranews.features.details.di.newsDetailsModule
import ir.rezazarchi.faranews.features.search.di.searchNewsModule
import org.koin.dsl.module

val navigationModule = module {
    includes(
        searchNewsModule,
        newsDetailsModule,
        bookmarkModule,
    )
}