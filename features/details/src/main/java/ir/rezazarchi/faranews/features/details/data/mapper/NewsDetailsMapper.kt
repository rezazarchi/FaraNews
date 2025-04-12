package ir.rezazarchi.faranews.features.details.data.mapper

import ir.rezazarchi.faranews.database.entity.NewsEntity
import ir.rezazarchi.faranews.features.details.domain.model.NewsDetails

object NewsDetailsMapper {
    fun NewsEntity.toNewsDetails(): NewsDetails {
        return NewsDetails(
            id = this.id,
            title = this.title,
            fullContent = this.content ?: "",
            imageUrl = this.urlToImage ?: "",
            date = this.publishedAt,
            author = this.author ?: "",
            source = this.source ?: "",
        )
    }
}