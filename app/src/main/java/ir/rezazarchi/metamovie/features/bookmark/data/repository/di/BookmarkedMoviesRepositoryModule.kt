package ir.rezazarchi.metamovie.features.bookmark.data.repository.di

import ir.rezazarchi.metamovie.features.bookmark.data.repository.BookmarkedMoviesRepositoryImplementation
import ir.rezazarchi.metamovie.features.bookmark.domain.repo.BookmarkedMoviesRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val bookmarkedMoviesRepositoryModule = module {
    singleOf(::BookmarkedMoviesRepositoryImplementation) bind BookmarkedMoviesRepository::class
}