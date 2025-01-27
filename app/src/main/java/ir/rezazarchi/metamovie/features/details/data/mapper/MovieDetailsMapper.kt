package ir.rezazarchi.metamovie.features.details.data.mapper

import ir.rezazarchi.metamovie.database.entity.MovieEntity
import ir.rezazarchi.metamovie.features.details.domain.model.MovieDetails
import ir.rezazarchi.metamovie.features.details.domain.model.MovieStatistics

object MovieDetailsMapper {
    fun MovieEntity.toMovieDetails(): MovieDetails {
        return MovieDetails(
            id = id,
            videoUrl = videoUrl,
            userNameUploader = username,
            tags = tags,
            movieStatistics = MovieStatistics(
                videoStats.views,
                videoStats.likes,
                videoStats.comments,
            )
        )
    }
}