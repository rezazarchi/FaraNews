package ir.rezazarchi.faranews.bookmark.data.mapper

import ir.rezazarchi.faranews.database.entity.NewsEntity
import ir.rezazarchi.faranews.bookmark.domain.model.BookmarkedNewsDetailed

object BookmarkedNewsMapper {
    fun NewsEntity.toBookmarkedNewsDetailed(): BookmarkedNewsDetailed {
        return BookmarkedNewsDetailed(
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