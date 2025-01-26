package ir.rezazarchi.metamovie.features.details.data.remote.di

import ir.rezazarchi.metamovie.core.di.RETROFIT
import ir.rezazarchi.metamovie.features.details.data.remote.service.MovieDetailsApiService
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit

val movieDetailsApiServiceModule = module {
    single {
        get<Retrofit>(named(RETROFIT)).create(MovieDetailsApiService::class.java)
    }
}