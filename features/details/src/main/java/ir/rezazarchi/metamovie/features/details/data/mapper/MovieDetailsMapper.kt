package ir.rezazarchi.metamovie.features.details.data.mapper

import ir.rezazarchi.metamovie.database.entity.NewsEntity
import ir.rezazarchi.metamovie.features.details.domain.model.NewsDetails

object MovieDetailsMapper {
    fun NewsEntity.toMovieDetails(): NewsDetails {
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