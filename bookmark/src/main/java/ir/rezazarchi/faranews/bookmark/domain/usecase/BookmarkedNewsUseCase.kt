package ir.rezazarchi.faranews.bookmark.domain.usecase

import ir.rezazarchi.faranews.core.data.Error
import ir.rezazarchi.faranews.core.data.Result
import ir.rezazarchi.faranews.bookmark.domain.model.BookmarkedNews
import ir.rezazarchi.faranews.bookmark.domain.repo.BookmarkedNewsRepository
import kotlinx.coroutines.flow.Flow

class BookmarkedNewsUseCase(private val repository: BookmarkedNewsRepository) {
    operator fun invoke(): Flow<Result<Set<BookmarkedNews>, Error>> {
        return repository.getBookmarkedNews()
    }

}