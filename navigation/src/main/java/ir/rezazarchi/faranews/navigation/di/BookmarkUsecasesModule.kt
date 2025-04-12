package ir.rezazarchi.faranews.navigation.di

import ir.rezazarchi.faranews.bookmark.data.repository.BookmarkedNewsRepositoryImplementation
import ir.rezazarchi.faranews.bookmark.domain.repo.BookmarkedNewsRepository
import ir.rezazarchi.faranews.bookmark.domain.usecase.BookmarkedNewsDetailedUseCase
import ir.rezazarchi.faranews.bookmark.domain.usecase.BookmarkedNewsUseCase
import ir.rezazarchi.faranews.bookmark.domain.usecase.IsBookmarkedUseCase
import ir.rezazarchi.faranews.bookmark.domain.usecase.ToggleBookmarkUseCase
import ir.rezazarchi.faranews.features.bookmark.presentation.viewmode.BookmarkedViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

val bookmarkModule = module {
    singleOf(::BookmarkedNewsRepositoryImplementation) bind BookmarkedNewsRepository::class
    factoryOf(::BookmarkedNewsDetailedUseCase)
    factoryOf(::BookmarkedNewsUseCase)
    factoryOf(::ToggleBookmarkUseCase)
    factoryOf(::IsBookmarkedUseCase)
    viewModelOf(::BookmarkedViewModel)
}