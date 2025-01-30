package ir.rezazarchi.metamovie.database.entity

import androidx.annotation.Keep
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import ir.rezazarchi.metamovie.database.converter.TagListConverter
import ir.rezazarchi.metamovie.database.converter.VideoStatsConverter

@Keep
@Entity(tableName = "movie")
data class MovieEntity(
    @PrimaryKey val id: Long = 0,
    val thumbnailUrl: String,
    val username: String,
    @TypeConverters(TagListConverter::class)
    val tags: List<String>,
    val videoUrl: String,
    @TypeConverters(VideoStatsConverter::class)
    val videoStats: VideoStats,
)