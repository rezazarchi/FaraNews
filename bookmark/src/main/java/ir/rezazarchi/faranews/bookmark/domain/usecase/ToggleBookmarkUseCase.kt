package ir.rezazarchi.faranews.bookmark.domain.usecase

import ir.rezazarchi.faranews.bookmark.domain.repo.BookmarkedNewsRepository

class ToggleBookmarkUseCase(val repository: BookmarkedNewsRepository) {
    suspend operator fun invoke(id: Long, bookmarked: Boolean) {
        if (bookmarked) {
            repository.addBookmarkNews(id)
        } else {
            repository.removeBookmarkNews(id)
        }
    }

}