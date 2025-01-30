package ir.rezazarchi.metamovie.features.bookmark.data.mapper

import ir.rezazarchi.metamovie.database.entity.MovieEntity
import ir.rezazarchi.metamovie.features.bookmark.domain.model.BookmarkedMovieDetailed

object BookmarkedMoviesMapper {
    fun MovieEntity.toBookmarkedMovieDetailed(): BookmarkedMovieDetailed {
        return BookmarkedMovieDetailed(
            id = this.id,
            videoThumbnail = thumbnailUrl,
            userNameUploader = username,
            tags = this.tags,
            bookmarked = true
        )
    }
}