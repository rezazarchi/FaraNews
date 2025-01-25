package ir.rezazarchi.metamovie.core.di

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

const val OK_HTTP = "OK_HTTP"
const val RETROFIT = "RETROFIT"
const val READ_TIMEOUT = "READ_TIMEOUT"
const val WRITE_TIMEOUT = "WRITE_TIMEOUT"
const val CONNECTION_TIMEOUT = "CONNECTION_TIMEOUT"
const val HTTP_LOGGING_INTERCEPTOR = "HTTP_LOGGING_INTERCEPTOR"
const val BASE_URL = "BASE_URL"

val retrofitModule = module {

    single<Long>(named(READ_TIMEOUT)) { 30 * 1000 }
    single<Long>(named(WRITE_TIMEOUT)) { 10 * 1000 }
    single<Long>(named(CONNECTION_TIMEOUT)) { 10 * 1000 }
    single(named(BASE_URL)) { "https://pixabay.com/api/v1/" }

    factory<Interceptor>(named(HTTP_LOGGING_INTERCEPTOR)) {
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.HEADERS)
            .setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    factory(named(OK_HTTP)) {
        OkHttpClient.Builder().readTimeout(get(named(READ_TIMEOUT)), TimeUnit.MILLISECONDS)
            .writeTimeout(get(named(WRITE_TIMEOUT)), TimeUnit.MILLISECONDS)
            .connectTimeout(get(named(CONNECTION_TIMEOUT)), TimeUnit.MILLISECONDS)
            .addInterceptor(get<Interceptor>(named(HTTP_LOGGING_INTERCEPTOR)))
            .build()
    }

    single(named(RETROFIT)) {
        Retrofit.Builder().client(get(named(OK_HTTP))).baseUrl(get<String>(named(BASE_URL)))
            .addConverterFactory(get())
            .build()
    }
}