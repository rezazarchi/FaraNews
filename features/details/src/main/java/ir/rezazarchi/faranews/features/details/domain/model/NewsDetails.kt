package ir.rezazarchi.faranews.features.details.domain.model

import java.time.Instant

data class NewsDetails(
    val id: Long,
    val title: String,
    val fullContent: String,
    val imageUrl: String,
    val date: Instant,
    val author: String,
    val source: String,
)