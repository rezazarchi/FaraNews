package ir.rezazarchi.metamovie.database.di

import ir.rezazarchi.metamovie.database.db.AppDataBase
import ir.rezazarchi.metamovie.database.db.DatabaseFactory
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val databaseFactoryModule = module {
    single<AppDataBase> { DatabaseFactory.getDatabase(androidApplication()) }
}