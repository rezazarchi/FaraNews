package ir.rezazarchi.faranews.features.details.data.remote.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import androidx.annotation.Keep

@Keep
@Serializable
data class MovieDetailsDto(
    @SerialName("total")
    val total: Int?, // 1
    @SerialName("totalHits")
    val totalHits: Int?, // 1
    @SerialName("hits")
    val hits: List<Hit>?
)