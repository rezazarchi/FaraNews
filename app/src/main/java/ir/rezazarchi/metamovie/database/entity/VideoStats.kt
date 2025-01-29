package ir.rezazarchi.metamovie.database.entity

import androidx.annotation.Keep
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class VideoStats(
    val views: Int,
    val likes: Int,
    val comments: Int,
)
