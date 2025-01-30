package ir.rezazarchi.metamovie.features.search.presentation.viewmode

import ir.rezazarchi.metamovie.core.utils.UiText

data class SearchMoviesState(
    val query: String = "",
    val movies: List<SearchedMovieWithBookmark> = emptyList(),
    val isLoading: Boolean = true,
    val error: UiText? = null,
)

sealed class SearchMoviesEffects {
    data class ShowSnackBar(val message: UiText) : SearchMoviesEffects()
}

sealed class SearchMoviesEvents {
    data class SearchForMovies(val query: String) : SearchMoviesEvents()
    data class OnSearchQueryChange(val query: String): SearchMoviesEvents()
    data class ToggleBookmark(val id: Long, val bookmarked: Boolean) : SearchMoviesEvents()
}