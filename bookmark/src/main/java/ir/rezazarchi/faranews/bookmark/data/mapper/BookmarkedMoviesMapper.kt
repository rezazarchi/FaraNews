package ir.rezazarchi.faranews.bookmark.data.mapper

import ir.rezazarchi.faranews.database.entity.NewsEntity
import ir.rezazarchi.faranews.bookmark.domain.model.BookmarkedMovieDetailed

object BookmarkedMoviesMapper {
    fun NewsEntity.toBookmarkedMovieDetailed(): BookmarkedMovieDetailed {
        return BookmarkedMovieDetailed(
            id = this.id,
            title = this.title,
            fullContent = this.content ?: "",
            imageUrl = this.urlToImage ?: "",
            date = this.publishedAt,
            author = this.author ?: "",
            source = this.source ?: "",
            bookmarked = true
        )
    }
}