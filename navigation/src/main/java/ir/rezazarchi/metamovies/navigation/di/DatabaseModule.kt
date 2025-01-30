package ir.rezazarchi.metamovies.navigation.di

import ir.rezazarchi.metamovie.database.db.AppDataBase
import ir.rezazarchi.metamovie.database.db.DatabaseFactory
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val databaseModule = module {
    single<AppDataBase> { DatabaseFactory.getDatabase(androidApplication()) }
    single { get<AppDataBase>().bookmarkedDao() }
    single { get<AppDataBase>().movieDao() }
}