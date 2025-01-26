package ir.rezazarchi.metamovie.database.di

import ir.rezazarchi.metamovie.database.db.AppDataBase
import org.koin.dsl.module

val moviesDaoModule = module {
    single { get<AppDataBase>().movieDao() }
}