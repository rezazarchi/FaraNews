package ir.rezazarchi.faranews.features.search.presentation.viewmode

import ir.rezazarchi.faranews.core.utils.UiText

data class SearchNewsState(
    val news: List<SearchedNewsWithBookmark> = emptyList(),
    val isLoading: Boolean = true,
    val error: UiText? = null,
)

sealed class SearchNewsEffects {
    data class ShowSnackBar(val message: UiText) : SearchNewsEffects()
}

sealed class SearchNewsEvents {
    data object SearchForNews : SearchNewsEvents()
    data class OnSearchQueryChange(val query: String): SearchNewsEvents()
    data class ToggleBookmark(val id: Long, val bookmarked: Boolean) : SearchNewsEvents()
}