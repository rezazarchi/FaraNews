package ir.rezazarchi.faranews.bookmark.domain.usecase

import ir.rezazarchi.faranews.core.data.Error
import ir.rezazarchi.faranews.core.data.Result
import ir.rezazarchi.faranews.bookmark.domain.model.BookmarkedMovie
import ir.rezazarchi.faranews.bookmark.domain.repo.BookmarkedMoviesRepository
import kotlinx.coroutines.flow.Flow

class BookmarkedMoviesUseCase(private val repository: BookmarkedMoviesRepository) {
    operator fun invoke(): Flow<Result<Set<BookmarkedMovie>, Error>> {
        return repository.getBookmarkedMovies()
    }

}