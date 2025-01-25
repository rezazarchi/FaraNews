package ir.rezazarchi.metamovie.features.bookmark.domain.repo

import ir.rezazarchi.metamovie.core.data.Error
import ir.rezazarchi.metamovie.core.data.Result
import ir.rezazarchi.metamovie.features.bookmark.domain.model.BookmarkedMovie
import kotlinx.coroutines.flow.Flow

interface BookmarkedMoviesRepository {
    fun getBookmarkedMovies(): Flow<Result<Set<BookmarkedMovie>, Error>>
}