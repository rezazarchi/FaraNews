package ir.rezazarchi.metamovie.features.bookmark.domain.usecase

import ir.rezazarchi.metamovie.core.data.Error
import ir.rezazarchi.metamovie.core.data.Result
import ir.rezazarchi.metamovie.features.bookmark.domain.model.BookmarkedMovie
import ir.rezazarchi.metamovie.features.bookmark.domain.repo.BookmarkedMoviesRepository
import kotlinx.coroutines.flow.Flow

class BookmarkedMoviesUseCase(private val repository: BookmarkedMoviesRepository) {
    operator fun invoke(): Flow<Result<Set<BookmarkedMovie>, Error>> {
        return repository.getBookmarkedMovies()
    }

}