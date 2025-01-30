package ir.rezazarchi.metamovie.features.bookmark.presentation.viewmode

import ir.rezazarchi.metamovie.core.utils.UiText
import ir.rezazarchi.metamovie.bookmark.domain.model.BookmarkedMovieDetailed

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