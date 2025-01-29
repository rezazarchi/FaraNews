package ir.rezazarchi.metamovie.features.details.data.remote.service

import ir.rezazarchi.metamovie.features.details.data.remote.dto.MovieDetailsDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieDetailsApiService {
    @GET(".")
    suspend fun getMovieDetails(
        @Query("id") movieId: Int,
        @Query("key") apiKey: String,
    ): Response<MovieDetailsDto>
}