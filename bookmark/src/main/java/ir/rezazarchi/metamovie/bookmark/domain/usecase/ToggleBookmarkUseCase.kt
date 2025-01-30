package ir.rezazarchi.metamovie.bookmark.domain.usecase

import ir.rezazarchi.metamovie.bookmark.domain.repo.BookmarkedMoviesRepository

class ToggleBookmarkUseCase(val repository: BookmarkedMoviesRepository) {
    suspend operator fun invoke(id: Long, bookmarked: Boolean) {
        if (bookmarked) {
            repository.addBookmarkMovie(id)
        } else {
            repository.removeBookmarkMovie(id)
        }
    }

}