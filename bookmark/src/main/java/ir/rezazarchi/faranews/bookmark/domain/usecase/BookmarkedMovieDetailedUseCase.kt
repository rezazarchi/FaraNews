package ir.rezazarchi.faranews.bookmark.domain.usecase

import ir.rezazarchi.faranews.bookmark.domain.model.BookmarkedMovieDetailed
import ir.rezazarchi.faranews.bookmark.domain.repo.BookmarkedMoviesRepository
import ir.rezazarchi.faranews.core.data.Error
import ir.rezazarchi.faranews.core.data.Result
import kotlinx.coroutines.flow.Flow

class BookmarkedMovieDetailedUseCase(private val repository: BookmarkedMoviesRepository) {
    operator fun invoke(): Flow<Result<List<BookmarkedMovieDetailed>, Error>> {
        return repository.getAllBookmarkedMovieDetailed()
    }
}