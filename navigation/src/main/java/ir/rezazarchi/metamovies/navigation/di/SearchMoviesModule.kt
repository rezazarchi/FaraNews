package ir.rezazarchi.metamovies.navigation.di

import ir.rezazarchi.metamovie.core.di.RETROFIT
import ir.rezazarchi.metamovie.features.search.data.remote.service.SearchMovieApiService
import ir.rezazarchi.metamovie.features.search.data.repository.SearchMoviesRepositoryImplementation
import ir.rezazarchi.metamovie.features.search.domain.repo.SearchMoviesRepository
import ir.rezazarchi.metamovie.features.search.domain.usecase.SearchMoviesUseCase
import ir.rezazarchi.metamovie.features.search.presentation.viewmode.SearchViewmodel
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.core.qualifier.named
import org.koin.dsl.bind
import org.koin.dsl.module
import retrofit2.Retrofit

val searchMoviesModule = module {
    single {
        get<Retrofit>(named(RETROFIT)).create(SearchMovieApiService::class.java)
    }
    singleOf(::SearchMoviesRepositoryImplementation) bind SearchMoviesRepository::class
    factoryOf(::SearchMoviesUseCase)
    viewModelOf(::SearchViewmodel)
}