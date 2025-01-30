package ir.rezazarchi.metamovie.bookmark.domain.usecase

import ir.rezazarchi.metamovie.core.data.Error
import ir.rezazarchi.metamovie.core.data.Result
import ir.rezazarchi.metamovie.bookmark.domain.repo.BookmarkedMoviesRepository
import kotlinx.coroutines.flow.Flow

class IsBookmarkedUseCase(private val repository: BookmarkedMoviesRepository) {
    operator fun invoke(movieId: Long): Flow<Result<Boolean, Error>> {
        return repository.isBookmarked(movieId)
    }

}