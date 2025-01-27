package ir.rezazarchi.metamovie.features.bookmark.data.repository

import ir.rezazarchi.metamovie.core.data.Result
import ir.rezazarchi.metamovie.database.dao.BookmarkedDao
import ir.rezazarchi.metamovie.database.entity.BookmarkedMovieEntity
import ir.rezazarchi.metamovie.features.bookmark.domain.model.BookmarkedMovie
import ir.rezazarchi.metamovie.features.bookmark.domain.repo.BookmarkedMoviesRepository
import kotlinx.coroutines.flow.map

class BookmarkedMoviesRepositoryImplementation(
    val bookmarkedDao: BookmarkedDao,
) : BookmarkedMoviesRepository {

    override fun getBookmarkedMovies() =
        bookmarkedDao.getAllBookmarkedMovies().map {
            Result.Success(it.map(BookmarkedMovieEntity::movieId).toSet())
        }

    override fun isBookmarked(movieId: Long) =
        bookmarkedDao.isBookmarked(movieId).map {
            Result.Success(it)
        }

    override suspend fun addBookmarkMovie(movie: BookmarkedMovie) {
        bookmarkedDao.insertBookmarkedMovie(BookmarkedMovieEntity(movie))
    }

    override suspend fun removeBookmarkMovie(movie: BookmarkedMovie) {
        bookmarkedDao.deleteBookmarkedMovie(BookmarkedMovieEntity(movie))
    }
}