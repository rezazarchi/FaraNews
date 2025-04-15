package ir.rezazarchi.faranews.features.details.di

import ir.rezazarchi.faranews.core.di.RETROFIT
import ir.rezazarchi.faranews.core.di.retrofitModule
import ir.rezazarchi.faranews.features.details.data.remote.service.NewsDetailsApiService
import ir.rezazarchi.faranews.features.details.data.repository.NewsDetailsRepositoryImplementation
import ir.rezazarchi.faranews.features.details.domain.repo.NewsDetailsRepository
import ir.rezazarchi.faranews.features.details.domain.usecase.GetNewsDetailsUseCase
import ir.rezazarchi.faranews.features.details.presentation.viewmode.NewsDetailsViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.core.qualifier.named
import org.koin.dsl.bind
import org.koin.dsl.module
import retrofit2.Retrofit

val newsDetailsModule = module {
    includes(retrofitModule)
    single {
        get<Retrofit>(named(RETROFIT)).create(NewsDetailsApiService::class.java)
    }
    singleOf(::NewsDetailsRepositoryImplementation) bind NewsDetailsRepository::class
    factoryOf(::GetNewsDetailsUseCase)
    viewModelOf(::NewsDetailsViewModel)
}