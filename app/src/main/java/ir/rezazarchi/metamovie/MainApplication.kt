package ir.rezazarchi.metamovie

import android.app.Application
import ir.rezazarchi.metamovie.core.di.jsonModule
import ir.rezazarchi.metamovie.core.di.retrofitModule
import ir.rezazarchi.metamovies.navigation.di.bookmarkModule
import ir.rezazarchi.metamovies.navigation.di.databaseModule
import ir.rezazarchi.metamovies.navigation.di.movieDetailsModule
import ir.rezazarchi.metamovies.navigation.di.searchMoviesModule
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