package ir.rezazarchi.metamovie.features.details.domain.model

data class MovieDetails(
    val id: Long,
    val videoUrl: String? = null,
    val userNameUploader: String,
    val tags: List<String> = emptyList(),
    val movieStatistics: MovieStatistics = MovieStatistics(0, 0, 0),
)