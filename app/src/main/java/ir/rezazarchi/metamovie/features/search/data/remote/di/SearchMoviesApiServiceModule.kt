package ir.rezazarchi.metamovie.features.search.data.remote.di

import ir.rezazarchi.metamovie.core.di.RETROFIT
import ir.rezazarchi.metamovie.features.search.data.remote.service.SearchMovieApiService
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit

val searchMoviesApiServiceModule = module {
    single {
        get<Retrofit>(named(RETROFIT)).create(SearchMovieApiService::class.java)
    }
}