package ir.rezazarchi.metamovie.features.details.data.remote.dto


import androidx.annotation.Keep
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class Videos(
    @SerialName("large") val large: Video?,
    @SerialName("medium") val medium: Video?,
    @SerialName("small") val small: Video?,
    @SerialName("tiny") val tiny: Video?
)