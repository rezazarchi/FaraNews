package ir.rezazarchi.faranews.database.fake

import ir.rezazarchi.faranews.database.dao.BookmarkedDao
import ir.rezazarchi.faranews.database.entity.BookmarkedMovieEntity
import ir.rezazarchi.faranews.database.entity.NewsEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flowOf

class FakeBookmarkedDao : BookmarkedDao {

    private val bookmarkedList = mutableListOf<BookmarkedMovieEntity>()
    private val bookmarkedFlow = MutableStateFlow<List<BookmarkedMovieEntity>>(emptyList())

    override fun getAllBookmarkedMovies(): Flow<List<BookmarkedMovieEntity>> {
        return bookmarkedFlow
    }

    override fun isBookmarked(movieId: Long): Flow<Boolean> {
        return flowOf(bookmarkedList.any { it.movieId == movieId })
    }

    override suspend fun upsertBookmarkedMovie(vararg bookmarkedMovie: BookmarkedMovieEntity) {
        bookmarkedList.removeAll { existing -> bookmarkedMovie.any { it.movieId == existing.movieId } }
        bookmarkedList.addAll(bookmarkedMovie)
        bookmarkedFlow.value = bookmarkedList.toList()
    }

    override suspend fun insertBookmarkedMovie(bookmarkedMovie: BookmarkedMovieEntity) {
        if (!bookmarkedList.any { it.movieId == bookmarkedMovie.movieId }) {
            bookmarkedList.add(bookmarkedMovie)
            bookmarkedFlow.value = bookmarkedList.toList()
        }
    }

    override suspend fun deleteBookmarkedMovie(bookmarkedMovie: BookmarkedMovieEntity) {
        bookmarkedList.remove(bookmarkedMovie)
        bookmarkedFlow.value = bookmarkedList.toList()
    }

    override fun getAllBookmarkedMoviesDetailed(): Flow<List<NewsEntity>> {
        return flowOf(emptyList()) // This requires a reference to the movies list to work properly
    }
}
