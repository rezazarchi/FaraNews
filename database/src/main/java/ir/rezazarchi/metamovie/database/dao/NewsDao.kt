package ir.rezazarchi.metamovie.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ir.rezazarchi.metamovie.database.entity.NewsEntity
import kotlinx.coroutines.flow.Flow
import java.time.Instant

@Dao
interface NewsDao {

    @Query("SELECT * FROM news")
    fun getAllNews(): Flow<List<NewsEntity>>

    @Query("SELECT * FROM news WHERE publishedAt BETWEEN :startOfYesterday AND :endOfYesterday")
    fun getYesterdayNews(startOfYesterday: Instant, endOfYesterday: Instant): Flow<List<NewsEntity>>

    @Query("SELECT * FROM news WHERE title = :title")
    fun getNewsByTitle(title: String): Flow<List<NewsEntity>>

    @Query("SELECT * FROM news WHERE id = :newsId")
    fun getNewsById(newsId: Long): Flow<NewsEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertNews(vararg news: NewsEntity)

}