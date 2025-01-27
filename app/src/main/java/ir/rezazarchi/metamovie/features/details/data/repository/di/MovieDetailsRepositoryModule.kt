package ir.rezazarchi.metamovie.features.details.data.repository.di

import ir.rezazarchi.metamovie.features.details.data.repository.MovieDetailsRepositoryImplementation
import ir.rezazarchi.metamovie.features.details.domain.repo.MovieDetailsRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val movieDetailsRepositoryModule = module {
    singleOf(::MovieDetailsRepositoryImplementation) bind MovieDetailsRepository::class
}