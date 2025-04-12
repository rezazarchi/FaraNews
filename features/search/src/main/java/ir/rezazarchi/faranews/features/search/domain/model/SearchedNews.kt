package ir.rezazarchi.faranews.features.search.domain.model

import androidx.annotation.Keep
import java.time.Instant

@Keep
data class SearchedNews(
    val id: Long,
    val title: String?,
    val imageUrl: String?,
    val shortBrief: String?,
    val queryName: String,
    val date: Instant,
)