package ir.rezazarchi.metamovie.features.search.data.remote.service

import ir.rezazarchi.metamovie.features.search.data.remote.dto.NewsResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchNewsApiService {
    @GET("everything")
    suspend fun searchNews(
        @Query("q") query: String,
        @Query("from") from: String,
        @Query("sortBy") sortBy: String,
        @Query("apiKey") apiKey: String,
    ): Response<NewsResponseDto>
}