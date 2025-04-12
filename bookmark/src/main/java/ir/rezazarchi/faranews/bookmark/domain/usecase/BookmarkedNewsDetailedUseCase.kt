package ir.rezazarchi.faranews.bookmark.domain.usecase

import ir.rezazarchi.faranews.bookmark.domain.model.BookmarkedNewsDetailed
import ir.rezazarchi.faranews.bookmark.domain.repo.BookmarkedNewsRepository
import ir.rezazarchi.faranews.core.data.Error
import ir.rezazarchi.faranews.core.data.Result
import kotlinx.coroutines.flow.Flow

class BookmarkedNewsDetailedUseCase(private val repository: BookmarkedNewsRepository) {
    operator fun invoke(): Flow<Result<List<BookmarkedNewsDetailed>, Error>> {
        return repository.getBookmarkedNewsDetailed()
    }
}