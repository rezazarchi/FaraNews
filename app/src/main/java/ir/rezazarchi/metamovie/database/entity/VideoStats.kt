package ir.rezazarchi.metamovie.database.entity

import androidx.annotation.Keep

@Keep
data class VideoStats(
    val views: Int,
    val likes: Int,
    val comments: Int,
)
