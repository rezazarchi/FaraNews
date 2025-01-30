package ir.rezazarchi.metamovie.features.details.data.remote.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import androidx.annotation.Keep

@Keep
@Serializable
data class Video(
    @SerialName("url")
    val url: String?, // https://cdn.pixabay.com/video/2022/12/18/143486-782758140_tiny.mp4
    @SerialName("width")
    val width: Int?, // 640
    @SerialName("height")
    val height: Int?, // 360
    @SerialName("size")
    val size: Int?, // 3623882
    @SerialName("thumbnail")
    val thumbnail: String? // https://cdn.pixabay.com/video/2022/12/18/143486-782758140_tiny.jpg
)