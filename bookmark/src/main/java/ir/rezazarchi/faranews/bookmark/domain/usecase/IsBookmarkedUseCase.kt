package ir.rezazarchi.faranews.bookmark.domain.usecase

import ir.rezazarchi.faranews.core.data.Error
import ir.rezazarchi.faranews.core.data.Result
import ir.rezazarchi.faranews.bookmark.domain.repo.BookmarkedNewsRepository
import kotlinx.coroutines.flow.Flow

class IsBookmarkedUseCase(private val repository: BookmarkedNewsRepository) {
    operator fun invoke(newsId: Long): Flow<Result<Boolean, Error>> {
        return repository.isBookmarked(newsId)
    }

}