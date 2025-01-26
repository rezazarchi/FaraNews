package ir.rezazarchi.metamovie.database.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ir.rezazarchi.metamovie.database.converter.TagListConverter
import ir.rezazarchi.metamovie.database.converter.VideoStatsConverter
import ir.rezazarchi.metamovie.database.dao.BookmarkedDao
import ir.rezazarchi.metamovie.database.dao.MoviesDao
import ir.rezazarchi.metamovie.database.entity.BookmarkedMovieEntity
import ir.rezazarchi.metamovie.database.entity.MovieEntity

@Database(
    entities = [
        MovieEntity::class,
        BookmarkedMovieEntity::class,
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(
    TagListConverter::class,
    VideoStatsConverter::class,
)
abstract class AppDataBase : RoomDatabase() {
    abstract fun movieDao(): MoviesDao
    abstract fun bookmarkedDao(): BookmarkedDao

}