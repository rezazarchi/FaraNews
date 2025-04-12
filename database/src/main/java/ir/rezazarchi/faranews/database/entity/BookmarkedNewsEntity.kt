package ir.rezazarchi.faranews.database.entity

import androidx.annotation.Keep
import androidx.room.Entity
import androidx.room.PrimaryKey

@Keep
@Entity(tableName = "bookmarked_news")
data class BookmarkedNewsEntity(
    @PrimaryKey
    var newsId: Long = 0
)