package ir.rezazarchi.metamovie.features.bookmark.domain.usecase

import ir.rezazarchi.metamovie.features.bookmark.domain.repo.BookmarkedMoviesRepository

class ToggleBookmarkUseCase(val repository: BookmarkedMoviesRepository) {
    suspend operator fun invoke(id: Long, bookmarked: Boolean) {
        if (bookmarked) {
            repository.addBookmarkMovie(id)
        } else {
            repository.removeBookmarkMovie(id)
        }
    }

}