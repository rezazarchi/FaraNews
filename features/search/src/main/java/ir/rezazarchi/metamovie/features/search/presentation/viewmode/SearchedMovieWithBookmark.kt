package ir.rezazarchi.metamovie.features.search.presentation.viewmode

import androidx.annotation.Keep
import ir.rezazarchi.metamovie.features.search.domain.model.SearchedNews

@Keep
data class SearchedMovieWithBookmark(
    val searchedNews: SearchedNews,
    val bookmarked: Boolean,
)