package ir.rezazarchi.metamovie.bookmark.domain.usecase

import ir.rezazarchi.metamovie.bookmark.domain.model.BookmarkedMovieDetailed
import ir.rezazarchi.metamovie.bookmark.domain.repo.BookmarkedMoviesRepository
import ir.rezazarchi.metamovie.core.data.Error
import ir.rezazarchi.metamovie.core.data.Result
import kotlinx.coroutines.flow.Flow

class BookmarkedMovieDetailedUseCase(private val repository: BookmarkedMoviesRepository) {
    operator fun invoke(): Flow<Result<List<BookmarkedMovieDetailed>, Error>> {
        return repository.getAllBookmarkedMovieDetailed()
    }
}