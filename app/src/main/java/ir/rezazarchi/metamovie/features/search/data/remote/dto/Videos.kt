package ir.rezazarchi.metamovie.features.search.data.remote.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import androidx.annotation.Keep

@Keep
@Serializable
data class Videos(
    @SerialName("large")
    val large: Video?,
    @SerialName("medium")
    val medium: Video?,
    @SerialName("small")
    val small: Video?,
    @SerialName("tiny")
    val tiny: Video?
)