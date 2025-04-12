package ir.rezazarchi.faranews.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Upsert
import ir.rezazarchi.faranews.database.entity.BookmarkedNewsEntity
import ir.rezazarchi.faranews.database.entity.NewsEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BookmarkedDao {
    @Query("SELECT * FROM bookmarked_news")
    fun getAllBookmarkedNews(): Flow<List<BookmarkedNewsEntity>>

    @Query("SELECT EXISTS(SELECT 1 FROM bookmarked_news WHERE newsId = :newsId)")
    fun isBookmarked(newsId: Long): Flow<Boolean>

    @Upsert
    suspend fun upsertBookmarkedNews(vararg bookmarkedNews: BookmarkedNewsEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertBookmarkedNews(bookmarkedNews: BookmarkedNewsEntity)

    @Delete
    suspend fun deleteBookmarkedNews(bookmarkedNews: BookmarkedNewsEntity)

    @Query("SELECT news.* FROM news INNER JOIN bookmarked_news ON news.id = bookmarked_news.newsId")
    fun getAllBookmarkedNewsDetailed(): Flow<List<NewsEntity>>
}