package ir.rezazarchi.metamovie.bookmark.data.mapper

import ir.rezazarchi.metamovie.database.entity.MovieEntity
import ir.rezazarchi.metamovie.bookmark.domain.model.BookmarkedMovieDetailed

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