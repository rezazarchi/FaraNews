package ir.rezazarchi.faranews.features.details.data.remote.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import androidx.annotation.Keep

@Keep
@Serializable
data class Hit(
    @SerialName("id")
    val id: Int?, // 143486
    @SerialName("pageURL")
    val pageURL: String?, // https://pixabay.com/videos/id-143486/
    @SerialName("type")
    val type: String?, // animation
    @SerialName("tags")
    val tags: String?, // lemon, yellow, fruit, food, nutrition, vitamins
    @SerialName("duration")
    val duration: Int?, // 30
    @SerialName("videos")
    val videos: Videos?,
    @SerialName("views")
    val views: Int?, // 36269
    @SerialName("downloads")
    val downloads: Int?, // 10487
    @SerialName("likes")
    val likes: Int?, // 138
    @SerialName("comments")
    val comments: Int?, // 19
    @SerialName("user_id")
    val userId: Int?, // 12431852
    @SerialName("user")
    val user: String?, // Nova2000
    @SerialName("userImageURL")
    val userImageURL: String? // https://cdn.pixabay.com/user/2019/05/09/11-32-49-915_250x250.jpg
)