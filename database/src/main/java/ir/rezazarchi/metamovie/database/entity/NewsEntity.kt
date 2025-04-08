package ir.rezazarchi.metamovie.database.entity

import androidx.annotation.Keep
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import ir.rezazarchi.metamovie.database.converter.DateTypeConverter
import java.time.Instant

@Keep
@Entity(
    tableName = "news",
    indices = [Index(value = ["title"], unique = true)]
)
data class NewsEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = -1,
    val source: String?,
    val author: String?,
    val title: String,
    val description: String?,
    val url: String?,
    val urlToImage: String?,
    @TypeConverters(DateTypeConverter::class)
    val publishedAt: Instant,
    val content: String?,
    val query: String,
)