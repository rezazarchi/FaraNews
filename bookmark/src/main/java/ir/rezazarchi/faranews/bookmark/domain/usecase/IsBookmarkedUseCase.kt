package ir.rezazarchi.faranews.bookmark.domain.usecase

import ir.rezazarchi.faranews.core.data.Error
import ir.rezazarchi.faranews.core.data.Result
import ir.rezazarchi.faranews.bookmark.domain.repo.BookmarkedMoviesRepository
import kotlinx.coroutines.flow.Flow

class IsBookmarkedUseCase(private val repository: BookmarkedMoviesRepository) {
    operator fun invoke(movieId: Long): Flow<Result<Boolean, Error>> {
        return repository.isBookmarked(movieId)
    }

}