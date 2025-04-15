package ir.rezazarchi.faranews.bookmark.di

import ir.rezazarchi.faranews.bookmark.data.repository.BookmarkedNewsRepositoryImplementation
import ir.rezazarchi.faranews.bookmark.domain.repo.BookmarkedNewsRepository
import ir.rezazarchi.faranews.bookmark.domain.usecase.BookmarkedNewsDetailedUseCase
import ir.rezazarchi.faranews.bookmark.domain.usecase.BookmarkedNewsUseCase
import ir.rezazarchi.faranews.bookmark.domain.usecase.IsBookmarkedUseCase
import ir.rezazarchi.faranews.bookmark.domain.usecase.ToggleBookmarkUseCase
import ir.rezazarchi.faranews.database.di.databaseModule
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val bookmarkCommonModule = module {
    includes(databaseModule)
    singleOf(::BookmarkedNewsRepositoryImplementation) bind BookmarkedNewsRepository::class
    factoryOf(::BookmarkedNewsDetailedUseCase)
    factoryOf(::BookmarkedNewsUseCase)
    factoryOf(::ToggleBookmarkUseCase)
    factoryOf(::IsBookmarkedUseCase)
}