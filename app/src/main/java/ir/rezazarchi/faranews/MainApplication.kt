package ir.rezazarchi.faranews

import android.app.Application
import ir.rezazarchi.faranews.core.di.jsonModule
import ir.rezazarchi.faranews.core.di.retrofitModule
import ir.rezazarchi.faranews.navigation.di.bookmarkModule
import ir.rezazarchi.faranews.navigation.di.databaseModule
import ir.rezazarchi.faranews.navigation.di.movieDetailsModule
import ir.rezazarchi.faranews.navigation.di.searchMoviesModule
import org.koin.android.ext.koin.androidContext
import org.koin.androix.startup.KoinStartup
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.dsl.koinConfiguration

@OptIn(KoinExperimentalAPI::class)
class MainApplication : Application(), KoinStartup {

    override fun onKoinStartup() = koinConfiguration {
        androidContext(this@MainApplication)
        modules(
            retrofitModule,
            jsonModule,
            searchMoviesModule,
            movieDetailsModule,
            databaseModule,
            bookmarkModule,
        )
    }

}