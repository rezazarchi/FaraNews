package ir.rezazarchi.metamovie.features.bookmark.domain.di

import ir.rezazarchi.metamovie.features.bookmark.domain.usecase.BookmarkedMovieDetailedUseCase
import ir.rezazarchi.metamovie.features.bookmark.domain.usecase.BookmarkedMoviesUseCase
import ir.rezazarchi.metamovie.features.bookmark.domain.usecase.ToggleBookmarkUseCase
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val bookmarksUseCasesModule = module {
    singleOf(::BookmarkedMovieDetailedUseCase)
    singleOf(::BookmarkedMoviesUseCase)
    singleOf(::ToggleBookmarkUseCase)
}