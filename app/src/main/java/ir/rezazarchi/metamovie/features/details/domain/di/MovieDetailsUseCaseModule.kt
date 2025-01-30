package ir.rezazarchi.metamovie.features.details.domain.di

import ir.rezazarchi.metamovie.features.details.domain.usecase.MovieDetailsUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val movieDetailsUseCaseModule = module {
    factoryOf(::MovieDetailsUseCase)
}