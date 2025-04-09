package ir.rezazarchi.metamovie.features.details.presentation.viewmode

import ir.rezazarchi.metamovie.core.utils.UiText
import ir.rezazarchi.metamovie.features.details.domain.model.NewsDetails

data class NewsDetailsState(
    val newsDetails: NewsDetails? = null,
    val isBookmarked: Boolean = false,
    val isLoading: Boolean = true,
    val error: UiText? = null,
)

sealed class NewsDetailsEffects {
    data class ShowSnackBar(val message: UiText) : NewsDetailsEffects()
}

sealed class NewsDetailsEvents {
    data class GetNewsDetails(val newsId: Long) : NewsDetailsEvents()
    data class ToggleBookmark(val newsId: Long, val bookmarked: Boolean) : NewsDetailsEvents()
}