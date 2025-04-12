package ir.rezazarchi.faranews.features.details.data.remote.service

import ir.rezazarchi.faranews.features.details.data.remote.dto.NewsDetailsDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsDetailsApiService {
    @GET(".")
    suspend fun getNewsDetails(
        @Query("id") newsId: Int,
        @Query("key") apiKey: String,
    ): Response<NewsDetailsDto>
}