package ir.rezazarchi.faranews.database.di

import ir.rezazarchi.faranews.database.db.AppDataBase
import ir.rezazarchi.faranews.database.db.DatabaseFactory
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val databaseModule = module {
    single<AppDataBase> { DatabaseFactory.getDatabase(androidApplication()) }
    single { get<AppDataBase>().bookmarkedDao() }
    single { get<AppDataBase>().newsDao() }
}