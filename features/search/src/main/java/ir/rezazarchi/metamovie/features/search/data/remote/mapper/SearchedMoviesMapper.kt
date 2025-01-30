package ir.rezazarchi.metamovie.features.search.data.remote.mapper

import ir.rezazarchi.metamovie.database.entity.MovieEntity
import ir.rezazarchi.metamovie.database.entity.VideoStats
import ir.rezazarchi.metamovie.features.search.data.remote.dto.SearchMovieDto
import ir.rezazarchi.metamovie.features.search.domain.model.SearchedMovie

object SearchedMoviesMapper {

    fun SearchMovieDto.toSearchedMovies(): List<SearchedMovie> {
        return this.hits?.map {
            SearchedMovie(
                id = it.id,
                videoThumbnail = it.videos?.tiny?.thumbnail ?: "",
                userNameUploader = it.user ?: "",
                tags = toTagsList(it.tags),
            )
        } ?: emptyList()
    }

    fun SearchMovieDto.toMovieEntity(): List<MovieEntity> {
        return this.hits?.map {
            MovieEntity(
                id = it.id,
                thumbnailUrl = it.videos?.tiny?.thumbnail ?: "",
                username = it.user ?: "",
                tags = toTagsList(it.tags),
                videoUrl = it.videos?.tiny?.url ?: "",
                videoStats = VideoStats(
                    it.views ?: 0,
                    it.likes ?: 0,
                    it.comments ?: 0,
                ),
            )
        } ?: emptyList()
    }

    fun MovieEntity.toSearchedMovie(): SearchedMovie {
        return SearchedMovie(
            id = this.id,
            videoThumbnail = this.thumbnailUrl,
            userNameUploader = this.username,
            tags = this.tags,
        )
    }

    private fun toTagsList(it: String?) = it?.split(",")?.map { it.trim() } ?: emptyList()

}