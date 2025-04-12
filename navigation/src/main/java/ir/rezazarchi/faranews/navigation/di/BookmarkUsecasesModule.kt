package ir.rezazarchi.faranews.navigation.di

import ir.rezazarchi.faranews.bookmark.data.repository.BookmarkedMoviesRepositoryImplementation
import ir.rezazarchi.faranews.bookmark.domain.repo.BookmarkedMoviesRepository
import ir.rezazarchi.faranews.bookmark.domain.usecase.BookmarkedMovieDetailedUseCase
import ir.rezazarchi.faranews.bookmark.domain.usecase.BookmarkedMoviesUseCase
import ir.rezazarchi.faranews.bookmark.domain.usecase.IsBookmarkedUseCase
import ir.rezazarchi.faranews.bookmark.domain.usecase.ToggleBookmarkUseCase
import ir.rezazarchi.faranews.features.bookmark.presentation.viewmode.BookmarkedViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

val bookmarkModule = module {
    singleOf(::BookmarkedMoviesRepositoryImplementation) bind BookmarkedMoviesRepository::class
    factoryOf(::BookmarkedMovieDetailedUseCase)
    factoryOf(::BookmarkedMoviesUseCase)
    factoryOf(::ToggleBookmarkUseCase)
    factoryOf(::IsBookmarkedUseCase)
    viewModelOf(::BookmarkedViewModel)
}