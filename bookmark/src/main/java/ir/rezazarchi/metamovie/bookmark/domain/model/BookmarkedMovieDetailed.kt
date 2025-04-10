package ir.rezazarchi.metamovie.bookmark.domain.model

import java.time.Instant

data class BookmarkedMovieDetailed(
    val id: Long,
    val title: String,
    val fullContent: String,
    val imageUrl: String,
    val date: Instant,
    val author: String,
    val source: String,
    val bookmarked: Boolean,
)
