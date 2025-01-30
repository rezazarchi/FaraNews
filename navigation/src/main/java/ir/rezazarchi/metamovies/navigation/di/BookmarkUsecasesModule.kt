package ir.rezazarchi.metamovies.navigation.di

import ir.rezazarchi.metamovie.bookmark.data.repository.BookmarkedMoviesRepositoryImplementation
import ir.rezazarchi.metamovie.bookmark.domain.repo.BookmarkedMoviesRepository
import ir.rezazarchi.metamovie.bookmark.domain.usecase.BookmarkedMovieDetailedUseCase
import ir.rezazarchi.metamovie.bookmark.domain.usecase.BookmarkedMoviesUseCase
import ir.rezazarchi.metamovie.bookmark.domain.usecase.IsBookmarkedUseCase
import ir.rezazarchi.metamovie.bookmark.domain.usecase.ToggleBookmarkUseCase
import ir.rezazarchi.metamovie.features.bookmark.presentation.viewmode.BookmarkedViewModel
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