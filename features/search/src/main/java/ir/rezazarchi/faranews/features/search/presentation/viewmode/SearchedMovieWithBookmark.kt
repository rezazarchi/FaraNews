package ir.rezazarchi.faranews.features.search.presentation.viewmode

import androidx.annotation.Keep
import ir.rezazarchi.faranews.features.search.domain.model.SearchedNews

@Keep
data class SearchedMovieWithBookmark(
    val searchedNews: SearchedNews,
    val bookmarked: Boolean,
)