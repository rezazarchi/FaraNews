package ir.rezazarchi.metamovie.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import ir.rezazarchi.metamovie.database.entity.MovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MoviesDao {

    @Query("SELECT * FROM movie")
    fun getAllMovies(): Flow<List<MovieEntity>>

    @Query("SELECT * FROM movie WHERE tags LIKE '%' || :tag || '%'")
    fun getMovieByTag(tag: String): Flow<List<MovieEntity>>

    @Upsert
    suspend fun upsertMovies(vararg movie: MovieEntity)

}