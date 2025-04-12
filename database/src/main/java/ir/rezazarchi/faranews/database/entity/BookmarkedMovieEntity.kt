package ir.rezazarchi.faranews.database.entity

import androidx.annotation.Keep
import androidx.room.Entity
import androidx.room.PrimaryKey

@Keep
@Entity(tableName = "bookmarked_movies")
data class BookmarkedMovieEntity(
    @PrimaryKey
    var movieId: Long = 0
)