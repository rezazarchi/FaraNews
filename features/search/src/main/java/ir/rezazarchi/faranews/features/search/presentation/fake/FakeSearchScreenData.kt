package ir.rezazarchi.faranews.features.search.presentation.fake

import ir.rezazarchi.faranews.features.search.domain.model.SearchedNews
import ir.rezazarchi.faranews.features.search.presentation.viewmode.SearchNewsState
import ir.rezazarchi.faranews.features.search.presentation.viewmode.SearchedNewsWithBookmark
import java.time.Instant

object FakeSearchScreenData {
    val searchResultItems = SearchNewsState(
        news = listOf(
            SearchedNewsWithBookmark(
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
            SearchedNewsWithBookmark(
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
            SearchedNewsWithBookmark(
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