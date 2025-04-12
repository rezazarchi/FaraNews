package ir.rezazarchi.faranews.database.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ir.rezazarchi.faranews.database.converter.DateTypeConverter
import ir.rezazarchi.faranews.database.dao.BookmarkedDao
import ir.rezazarchi.faranews.database.dao.NewsDao
import ir.rezazarchi.faranews.database.entity.BookmarkedMovieEntity
import ir.rezazarchi.faranews.database.entity.NewsEntity

@Database(
    entities = [
        NewsEntity::class,
        BookmarkedMovieEntity::class,
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(
    DateTypeConverter::class,
)
abstract class AppDataBase : RoomDatabase() {
    abstract fun newsDao(): NewsDao
    abstract fun bookmarkedDao(): BookmarkedDao

}