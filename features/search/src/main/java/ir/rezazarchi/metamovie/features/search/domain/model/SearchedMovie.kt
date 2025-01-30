package ir.rezazarchi.metamovie.features.search.domain.model

data class SearchedMovie(
    val id: Long,
    val videoThumbnail: String,
    val userNameUploader: String,
    val tags: List<String> = emptyList(),
)