package ir.rezazarchi.metamovie.features.bookmark.domain.model

data class BookmarkedMovieDetailed(
    val id: Long,
    val videoThumbnail: String,
    val userNameUploader: String,
    val tags: List<String> = emptyList(),
    val bookmarked: Boolean,
)
