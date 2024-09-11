package com.ahmed.amdbmoviesapp.network

import com.ahmed.amdbmoviesapp.entities.MoviePage
import com.ahmed.amdbmoviesapp.entities.TvPage
import retrofit2.http.GET
import retrofit2.Response
import retrofit2.http.Query

interface TMDbApiService {
    @GET("movie/popular")
    suspend fun getPopularMovies(@Query("language") language: String = "en-US"): Response<MoviePage>

    @GET("tv/popular")
    suspend fun getPopularSeries(@Query("language") language: String = "en-US"): Response<TvPage>

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(@Query("language") language: String = "en-US"): Response<MoviePage>

    @GET("tv/top_rated")
    suspend fun getTopRatedSeries(@Query("language") language: String = "en-US"): Response<TvPage>
}