package com.ahmed.amdbmoviesapp.network

import com.ahmed.amdbmoviesapp.entities.AiringTodayPage
import com.ahmed.amdbmoviesapp.entities.MovieCreditsResponse
import com.ahmed.amdbmoviesapp.entities.MovieDetailsPage
import com.ahmed.amdbmoviesapp.entities.MoviePage
import com.ahmed.amdbmoviesapp.entities.MovieSearchPage
import com.ahmed.amdbmoviesapp.entities.MoviesTrailerPage
import com.ahmed.amdbmoviesapp.entities.OnAirTv
import com.ahmed.amdbmoviesapp.entities.OnAirTvResponse
import com.ahmed.amdbmoviesapp.entities.TvCreditsResponse
import com.ahmed.amdbmoviesapp.entities.TvDetailsPage
import com.ahmed.amdbmoviesapp.entities.TvPage
import com.ahmed.amdbmoviesapp.entities.TvSearchPage
import com.ahmed.amdbmoviesapp.entities.TvsTrailerPage
import com.ahmed.amdbmoviesapp.entities.UpcomingMovieResponse
import retrofit2.http.GET
import retrofit2.Response
import retrofit2.http.Path
import retrofit2.http.Query

interface TMDbApiService {
    @GET("trending/movie/day")
    suspend fun getPopularMovies(@Query("api_key") apiKey: String = "e2669d4bb6934afe70f92afab29a8d4b"): Response<MoviePage>

    @GET("tv/popular")
    suspend fun getPopularSeries(@Query("language") language: String = "en-US",@Query("api_key") apiKey: String = "e2669d4bb6934afe70f92afab29a8d4b"): Response<TvPage>

    @GET("movie/upcoming")
    suspend fun getUpcomingMovies(
        @Query("api_key") apiKey: String = "e2669d4bb6934afe70f92afab29a8d4b",
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1
    ): Response<UpcomingMovieResponse>


    @GET("tv/on_the_air")
    suspend fun getOnAirTvShows(
        @Query("api_key") apiKey: String = "e2669d4bb6934afe70f92afab29a8d4b",
        @Query("page") page: Int = 1
    ): Response<OnAirTvResponse>

    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(@Path("movie_id") movieId: Int, @Query("language") language: String = "en-US",@Query("api_key") apiKey: String = "e2669d4bb6934afe70f92afab29a8d4b"): Response<MovieDetailsPage>

    @GET("tv/{tv_id}")
    suspend fun getSeriesDetails(@Path("tv_id") tvId: Int, @Query("language") language: String = "en-US",@Query("api_key") apiKey: String = "e2669d4bb6934afe70f92afab29a8d4b"): Response<TvDetailsPage>

    @GET("search/movie")
    suspend fun searchMovies(@Query("query") query: String, @Query("language") language: String = "en-US",@Query("api_key") apiKey: String = "e2669d4bb6934afe70f92afab29a8d4b"): Response<MovieSearchPage>

    @GET("search/tv")
    suspend fun searchSeries(@Query("query") query: String, @Query("language") language: String = "en-US",@Query("api_key") apiKey: String = "e2669d4bb6934afe70f92afab29a8d4b"): Response<TvSearchPage>

    @GET("movie/{movie_id}/videos")
    suspend fun getMovieTrailers(@Path("movie_id") movieId: Int, @Query("language") language: String = "en-US",@Query("api_key") apiKey: String = "e2669d4bb6934afe70f92afab29a8d4b"): Response<MoviesTrailerPage>

    @GET("tv/{tv_id}/videos")
    suspend fun getSeriesTrailers(@Path("tv_id") tvId: Int, @Query("language") language: String = "en-US",@Query("api_key") apiKey: String = "e2669d4bb6934afe70f92afab29a8d4b"): Response<TvsTrailerPage>

    @GET("movie/{movie_id}/credits")
    suspend fun getMovieCredits(@Path("movie_id") movieId: Int,@Query("api_key") apiKey: String = "e2669d4bb6934afe70f92afab29a8d4b"): Response<MovieCreditsResponse>

    @GET("tv/{tv_id}/credits")
    suspend fun getTvCredits(@Path("tv_id") movieId: Int,@Query("api_key") apiKey: String = "e2669d4bb6934afe70f92afab29a8d4b"): Response<TvCreditsResponse>


}