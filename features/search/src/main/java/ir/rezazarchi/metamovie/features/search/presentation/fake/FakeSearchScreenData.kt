package ir.rezazarchi.metamovie.features.search.presentation.fake

import ir.rezazarchi.metamovie.features.search.domain.model.SearchedNews
import ir.rezazarchi.metamovie.features.search.presentation.viewmode.SearchMoviesState
import ir.rezazarchi.metamovie.features.search.presentation.viewmode.SearchedMovieWithBookmark
import java.time.Instant

object FakeSearchScreenData {
    val searchResultItems = SearchMoviesState(
        movies = listOf(
            SearchedMovieWithBookmark(
                SearchedNews(
                    id = 1L,
                    title = "AI Revolutionizes Android Development",
                    imageUrl = "https://via.placeholder.com/150/FF5733",
                    shortBrief = "New tools powered by AI are changing how developers build Android apps.",
                    queryName = "android",
                    date = Instant.now().minusSeconds(3600) // 1 hour ago
                ),
                bookmarked = false
            ),
            SearchedMovieWithBookmark(
                searchedNews = SearchedNews(
                    id = 2L,
                    title = "Kotlin 2.0 Preview Released",
                    imageUrl = "https://via.placeholder.com/150/33C1FF",
                    shortBrief = "JetBrains previews exciting features in Kotlin 2.0, boosting productivity.",
                    queryName = "kotlin",
                    date = Instant.now().minusSeconds(7200) // 2 hours ago
                ),
                bookmarked = false,
            ),
            SearchedMovieWithBookmark(
                SearchedNews(
                    id = 3L,
                    title = "Room Database Tips You Didn't Know",
                    imageUrl = "https://via.placeholder.com/150/75FF33",
                    shortBrief = "Discover lesser-known but powerful features in Room for better data handling.",
                    queryName = "room",
                    date = Instant.now().minusSeconds(10800) // 3 hours ago
                ),
                bookmarked = true,
            )
        ),
        isLoading = false,
        error = null,
    )
}