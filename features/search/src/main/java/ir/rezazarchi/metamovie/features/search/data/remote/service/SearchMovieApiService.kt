package ir.rezazarchi.metamovie.features.search.data.remote.service

import ir.rezazarchi.metamovie.features.search.data.remote.dto.SearchMovieDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchMovieApiService {
    @GET(".")
    suspend fun searchMovies(
        @Query("q") query: String,
        @Query("key") apiKey: String,
    ): Response<SearchMovieDto>
}