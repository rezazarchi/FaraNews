package ir.rezazarchi.metamovie.features.search.domain.di

import ir.rezazarchi.metamovie.features.search.domain.usecase.SearchMoviesUseCase
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val searchMoviesUseCaseModule = module {
    singleOf(::SearchMoviesUseCase)
}