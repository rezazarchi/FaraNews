package ir.rezazarchi.metamovie

import android.app.Application
import ir.rezazarchi.metamovie.core.di.jsonModule
import ir.rezazarchi.metamovie.core.di.retrofitModule
import ir.rezazarchi.metamovie.database.di.bookmarkedDaoModule
import ir.rezazarchi.metamovie.database.di.databaseFactoryModule
import ir.rezazarchi.metamovie.database.di.moviesDaoModule
import ir.rezazarchi.metamovie.features.bookmark.data.repository.di.bookmarkedMoviesRepositoryModule
import ir.rezazarchi.metamovie.features.bookmark.domain.di.bookmarksUseCasesModule
import ir.rezazarchi.metamovie.features.bookmark.presentation.viewmode.bookmarkedViewModelModule
import ir.rezazarchi.metamovie.features.details.data.remote.di.movieDetailsApiServiceModule
import ir.rezazarchi.metamovie.features.details.data.repository.di.movieDetailsRepositoryModule
import ir.rezazarchi.metamovie.features.details.domain.di.movieDetailsUseCaseModule
import ir.rezazarchi.metamovie.features.details.presentation.viewmode.movieDetailsViewModelModule
import ir.rezazarchi.metamovie.features.search.data.remote.di.searchMoviesApiServiceModule
import ir.rezazarchi.metamovie.features.search.data.repository.di.searchMoviesRepositoryModule
import ir.rezazarchi.metamovie.features.search.domain.di.searchMoviesUseCaseModule
import ir.rezazarchi.metamovie.features.search.presentation.viewmode.searchViewModelModule
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
            searchMoviesApiServiceModule,
            movieDetailsApiServiceModule,
            databaseFactoryModule,
            moviesDaoModule,
            bookmarkedDaoModule,
            searchMoviesRepositoryModule,
            movieDetailsRepositoryModule,
            bookmarkedMoviesRepositoryModule,
            searchViewModelModule,
            searchMoviesUseCaseModule,
            bookmarksUseCasesModule,
            bookmarkedViewModelModule,
            movieDetailsUseCaseModule,
            movieDetailsViewModelModule,
        )
    }

}