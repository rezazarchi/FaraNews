package ir.rezazarchi.metamovie.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Upsert
import ir.rezazarchi.metamovie.database.entity.BookmarkedMovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BookmarkedDao {
    @Query("SELECT * FROM bookmarked_movies")
    fun getAllBookmarkedMovies(): Flow<List<BookmarkedMovieEntity>>

    @Query("SELECT EXISTS(SELECT 1 FROM bookmarked_movies WHERE movieId = :movieId)")
    fun isBookmarked(movieId: Int): Flow<Boolean>

    @Upsert
    suspend fun upsertBookmarkedMovie(vararg bookmarkedMovie: BookmarkedMovieEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertBookmarkedMovie(bookmarkedMovie: BookmarkedMovieEntity)

    @Delete
    suspend fun deleteBookmarkedMovie(bookmarkedMovie: BookmarkedMovieEntity)
}