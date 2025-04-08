package ir.rezazarchi.metamovie.features.search.data.remote.dto


import androidx.annotation.Keep
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class ArticleDto(
    @SerialName("source")
    val source: SourceDto? = null,
    @SerialName("author")
    val author: String? = null,
    @SerialName("title")
    val title: String,
    @SerialName("description")
    val description: String,
    @SerialName("url")
    val url: String,
    @SerialName("urlToImage")
    val urlToImage: String? = null,
    @SerialName("publishedAt")
    val publishedAt: String,
    @SerialName("content")
    val content: String,
)