package ir.rezazarchi.metamovie.features.details.presentation.viewmode

import ir.rezazarchi.metamovie.core.utils.UiText
import ir.rezazarchi.metamovie.features.details.domain.model.MovieDetails

data class MovieDetailsState(
    val movieDetails: MovieDetails? = null,
    val isBookmarked: Boolean = false,
    val isLoading: Boolean = true,
    val error: UiText? = null,
)

sealed class MovieDetailsEffects {
    data class ShowSnackBar(val message: UiText) : MovieDetailsEffects()
}

sealed class MovieDetailsEvents {
    data class GetMovieDetails(val movieId: Long) : MovieDetailsEvents()
    data class ToggleBookmark(val movieId: Long, val bookmarked: Boolean) : MovieDetailsEvents()
}