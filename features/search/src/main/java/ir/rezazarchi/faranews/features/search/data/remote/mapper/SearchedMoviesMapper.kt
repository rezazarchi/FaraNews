package ir.rezazarchi.faranews.features.search.data.remote.mapper

import ir.rezazarchi.faranews.database.entity.NewsEntity
import ir.rezazarchi.faranews.features.search.data.remote.dto.NewsResponseDto
import ir.rezazarchi.faranews.features.search.domain.model.SearchedNews
import java.time.Instant

object SearchedMoviesMapper {

    fun NewsResponseDto.toNewsEntity(query: String): List<NewsEntity> {
        return this.articles.map {
            NewsEntity(
                id = 0,
                source = it.source?.name,
                author = it.author,
                title = it.title,
                description = it.description,
                url = it.url,
                urlToImage = it.urlToImage,
                publishedAt = Instant.parse(it.publishedAt),
                content = it.content,
                query = query
            )
        }
    }

    fun NewsEntity.toSearchedNews(): SearchedNews {
        return SearchedNews(
            id = this.id,
            title = this.title,
            imageUrl = this.urlToImage,
            shortBrief = this.description,
            queryName = this.query,
            date = this.publishedAt,
        )
    }

}