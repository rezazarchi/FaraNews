package ir.rezazarchi.metamovie.features.search.data.repository.di

import ir.rezazarchi.metamovie.features.search.data.repository.SearchMoviesRepositoryImplementation
import ir.rezazarchi.metamovie.features.search.domain.repo.SearchMoviesRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val searchMoviesRepositoryModule = module {
    singleOf(::SearchMoviesRepositoryImplementation) bind SearchMoviesRepository::class
}