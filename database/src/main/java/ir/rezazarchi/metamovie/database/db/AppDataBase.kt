package ir.rezazarchi.metamovie.database.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ir.rezazarchi.metamovie.database.converter.DateTypeConverter
import ir.rezazarchi.metamovie.database.dao.BookmarkedDao
import ir.rezazarchi.metamovie.database.dao.NewsDao
import ir.rezazarchi.metamovie.database.entity.BookmarkedMovieEntity
import ir.rezazarchi.metamovie.database.entity.NewsEntity

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