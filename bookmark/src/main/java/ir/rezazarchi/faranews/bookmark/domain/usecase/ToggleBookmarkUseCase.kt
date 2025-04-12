package ir.rezazarchi.faranews.bookmark.domain.usecase

import ir.rezazarchi.faranews.bookmark.domain.repo.BookmarkedMoviesRepository

class ToggleBookmarkUseCase(val repository: BookmarkedMoviesRepository) {
    suspend operator fun invoke(id: Long, bookmarked: Boolean) {
        if (bookmarked) {
            repository.addBookmarkMovie(id)
        } else {
            repository.removeBookmarkMovie(id)
        }
    }

}