package ir.rezazarchi.faranews.navigation.di

import ir.rezazarchi.faranews.core.di.RETROFIT
import ir.rezazarchi.faranews.features.search.data.remote.service.SearchNewsApiService
import ir.rezazarchi.faranews.features.search.data.repository.SearchNewsRepositoryImplementation
import ir.rezazarchi.faranews.features.search.domain.repo.SearchNewsRepository
import ir.rezazarchi.faranews.features.search.domain.usecase.SearchNewsUseCase
import ir.rezazarchi.faranews.features.search.presentation.viewmode.SearchViewmodel
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.core.qualifier.named
import org.koin.dsl.bind
import org.koin.dsl.module
import retrofit2.Retrofit

val searchNewsModule = module {
    single {
        get<Retrofit>(named(RETROFIT)).create(SearchNewsApiService::class.java)
    }
    singleOf(::SearchNewsRepositoryImplementation) bind SearchNewsRepository::class
    factoryOf(::SearchNewsUseCase)
    viewModelOf(::SearchViewmodel)
}