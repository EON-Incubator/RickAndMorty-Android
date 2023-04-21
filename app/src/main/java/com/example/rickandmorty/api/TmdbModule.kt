package com.example.rickandmorty.api

import com.example.rickandmorty.R
import com.example.rickandmorty.domain.episodes.TmdbEpisodeDetail
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

const val BASE_URL = "https://api.themoviedb.org/3/tv/60625/"

interface APIService {
    @Headers(
        "Accept: application/json"
    )
    @GET("season/{season}/episode/{episode}?api_key=d4bec6682b7448844a2bcbe5cd70512f&append_to_response=images,videos")
    suspend fun getEpisodeDetails(
        @Path(R.string.season_all_small_case.toString()) season: Int,
        @Path(R.string.episode_all_small_case.toString()) episode: Int,
    ): TmdbEpisodeDetail

    companion object {
        private val interceptor: HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        private val client: OkHttpClient = OkHttpClient.Builder().apply {
            addInterceptor(interceptor)
        }.build()
        var apiService: APIService? = null
        fun getInstance(): APIService {
            if (apiService == null) {
                apiService = Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(BASE_URL)
                    .client(client)
                    .build().create(APIService::class.java)
            }
            return apiService!!
        }
    }
}