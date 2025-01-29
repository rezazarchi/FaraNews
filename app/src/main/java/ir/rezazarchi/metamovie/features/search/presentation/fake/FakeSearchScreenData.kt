package ir.rezazarchi.metamovie.features.search.presentation.fake

import ir.rezazarchi.metamovie.features.search.domain.model.SearchedMovie
import ir.rezazarchi.metamovie.features.search.presentation.viewmode.SearchMoviesState
import ir.rezazarchi.metamovie.features.search.presentation.viewmode.SearchedMovieWithBookmark

object FakeSearchScreenData {
    val searchResultItems = SearchMoviesState(
        movies = listOf(
            SearchedMovieWithBookmark(
                SearchedMovie(
                    1,
                    "143486-782758140_tiny.jpg",
                    "RezaZ",
                    listOf("lemon", "yellow", "fruit", "food", "nutrition", "fake")
                ),
                bookmarked = true,
            ),
            SearchedMovieWithBookmark(
                SearchedMovie(
                    2,
                    "143486-782758140_tiny.jpg",
                    "RezaZ",
                    listOf("horror", "+18", "fake")
                ),
                bookmarked = false,
            ),
            SearchedMovieWithBookmark(
                SearchedMovie(
                    3,
                    "143486-782758140_tiny.jpg",
                    "RezaZ",
                    listOf("romantic", "family", "fake")
                ),
                bookmarked = true,
            )
        ),
        query = "",
        isLoading = false,
        error = null,
    )
}