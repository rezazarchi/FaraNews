package ir.rezazarchi.metamovie.features.search.data.remote.dto


import androidx.annotation.Keep
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class NewsResponseDto(
    @SerialName("status")
    val status: String,
    @SerialName("totalResults")
    val totalResults: Int = 0,
    @SerialName("articles")
    val articles: List<ArticleDto> = emptyList(),
)