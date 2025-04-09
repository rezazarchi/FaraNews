package ir.rezazarchi.metamovie.features.search.presentation.viewmode

import ir.rezazarchi.metamovie.core.utils.UiText

data class SearchMoviesState(
    val movies: List<SearchedMovieWithBookmark> = emptyList(),
    val isLoading: Boolean = true,
    val error: UiText? = null,
)

sealed class SearchMoviesEffects {
    data class ShowSnackBar(val message: UiText) : SearchMoviesEffects()
}

sealed class SearchMoviesEvents {
    data object SearchForMovies : SearchMoviesEvents()
    data class OnSearchQueryChange(val query: String): SearchMoviesEvents()
    data class ToggleBookmark(val id: Long, val bookmarked: Boolean) : SearchMoviesEvents()
}