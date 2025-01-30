package ir.rezazarchi.metamovie.bookmark.domain.repo

import ir.rezazarchi.metamovie.core.data.Error
import ir.rezazarchi.metamovie.core.data.Result
import ir.rezazarchi.metamovie.bookmark.domain.model.BookmarkedMovie
import ir.rezazarchi.metamovie.bookmark.domain.model.BookmarkedMovieDetailed
import kotlinx.coroutines.flow.Flow

interface BookmarkedMoviesRepository {
    fun getBookmarkedMovies(): Flow<Result<Set<BookmarkedMovie>, Error>>
    fun isBookmarked(movieId: Long): Flow<Result<Boolean, Error>>
    suspend fun addBookmarkMovie(movie: BookmarkedMovie)
    suspend fun removeBookmarkMovie(movie: BookmarkedMovie)
    fun getAllBookmarkedMovieDetailed(): Flow<Result<List<BookmarkedMovieDetailed>, Error>>
}