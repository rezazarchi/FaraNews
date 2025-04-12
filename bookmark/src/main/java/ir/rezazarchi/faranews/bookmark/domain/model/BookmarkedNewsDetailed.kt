package ir.rezazarchi.faranews.bookmark.domain.model

import java.time.Instant

data class BookmarkedNewsDetailed(
    val id: Long,
    val title: String,
    val fullContent: String,
    val imageUrl: String,
    val date: Instant,
    val author: String,
    val source: String,
    val bookmarked: Boolean,
)
