package ir.rezazarchi.metamovie.core.di

import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import org.koin.dsl.module
import retrofit2.converter.kotlinx.serialization.asConverterFactory

val jsonModule = module {
    single<Json> { Json { ignoreUnknownKeys = true } }
    single { get<Json>().asConverterFactory("application/json".toMediaType()) }
}