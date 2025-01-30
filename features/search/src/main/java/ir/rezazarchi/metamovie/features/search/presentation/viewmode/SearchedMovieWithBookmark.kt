package ir.rezazarchi.metamovie.features.search.presentation.viewmode

import androidx.annotation.Keep
import ir.rezazarchi.metamovie.features.search.domain.model.SearchedMovie

@Keep
data class SearchedMovieWithBookmark(
    val searchedMovie: SearchedMovie,
    val bookmarked: Boolean,
)