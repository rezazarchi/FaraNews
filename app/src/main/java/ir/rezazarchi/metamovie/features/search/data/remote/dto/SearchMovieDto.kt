package ir.rezazarchi.metamovie.features.search.data.remote.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import androidx.annotation.Keep

@Keep
@Serializable
data class SearchMovieDto(
    @SerialName("total")
    val total: Int?, // 1455
    @SerialName("totalHits")
    val totalHits: Int?, // 500
    @SerialName("hits")
    val hits: List<Hit?>?
)