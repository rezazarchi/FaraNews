package ir.rezazarchi.faranews.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Upsert
import ir.rezazarchi.faranews.database.entity.BookmarkedMovieEntity
import ir.rezazarchi.faranews.database.entity.NewsEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BookmarkedDao {
    @Query("SELECT * FROM bookmarked_movies")
    fun getAllBookmarkedMovies(): Flow<List<BookmarkedMovieEntity>>

    @Query("SELECT EXISTS(SELECT 1 FROM bookmarked_movies WHERE movieId = :movieId)")
    fun isBookmarked(movieId: Long): Flow<Boolean>

    @Upsert
    suspend fun upsertBookmarkedMovie(vararg bookmarkedMovie: BookmarkedMovieEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertBookmarkedMovie(bookmarkedMovie: BookmarkedMovieEntity)

    @Delete
    suspend fun deleteBookmarkedMovie(bookmarkedMovie: BookmarkedMovieEntity)

    @Query("SELECT news.* FROM news INNER JOIN bookmarked_movies ON news.id = bookmarked_movies.movieId")
    fun getAllBookmarkedMoviesDetailed(): Flow<List<NewsEntity>>
}