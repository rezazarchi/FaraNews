package ir.rezazarchi.metamovie.database.entity

import androidx.annotation.Keep
import androidx.room.Entity
import androidx.room.PrimaryKey

@Keep
@Entity(tableName = "bookmarked_movies")
class BookmarkedMovieEntity {
    @PrimaryKey
    var movieId: Long = 0

}