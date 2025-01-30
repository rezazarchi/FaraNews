package ir.rezazarchi.metamovie.features.search.data.remote.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import androidx.annotation.Keep

@Keep
@Serializable
data class Video(
    @SerialName("url")
    val url: String?, // https://cdn.pixabay.com/video/2022/12/18/143486-782758140_small.mp4
    @SerialName("width")
    val width: Int?, // 960
    @SerialName("height")
    val height: Int?, // 540
    @SerialName("size")
    val size: Int?, // 6078447
    @SerialName("thumbnail")
    val thumbnail: String? // https://cdn.pixabay.com/video/2022/12/18/143486-782758140_small.jpg
)