package ir.rezazarchi.metamovies.navigation.di

import ir.rezazarchi.metamovie.core.di.RETROFIT
import ir.rezazarchi.metamovie.features.details.data.remote.service.MovieDetailsApiService
import ir.rezazarchi.metamovie.features.details.data.repository.MovieDetailsRepositoryImplementation
import ir.rezazarchi.metamovie.features.details.domain.repo.MovieDetailsRepository
import ir.rezazarchi.metamovie.features.details.domain.usecase.MovieDetailsUseCase
import ir.rezazarchi.metamovie.features.details.presentation.viewmode.MovieDetailsViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.core.qualifier.named
import org.koin.dsl.bind
import org.koin.dsl.module
import retrofit2.Retrofit

val movieDetailsModule = module {
    single {
        get<Retrofit>(named(RETROFIT)).create(MovieDetailsApiService::class.java)
    }
    singleOf(::MovieDetailsRepositoryImplementation) bind MovieDetailsRepository::class
    factoryOf(::MovieDetailsUseCase)
    viewModelOf(::MovieDetailsViewModel)
}