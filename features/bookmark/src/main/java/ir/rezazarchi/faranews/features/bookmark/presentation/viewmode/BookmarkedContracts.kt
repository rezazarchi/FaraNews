package ir.rezazarchi.faranews.features.bookmark.presentation.viewmode

import ir.rezazarchi.faranews.core.utils.UiText
import ir.rezazarchi.faranews.bookmark.domain.model.BookmarkedMovieDetailed

class BookmarkedContracts {

    data class BookmarkedState(
        val movies: List<BookmarkedMovieDetailed> = emptyList(),
        val isLoading: Boolean = true,
        val error: UiText? = null,
    )

    sealed class BookmarkedEffects {
        data class ShowSnackBar(val message: UiText) : BookmarkedEffects()
    }

    sealed class BookmarkedEvents {
        data class RemoveBookmark(val id: Long) : BookmarkedEvents()
    }
}