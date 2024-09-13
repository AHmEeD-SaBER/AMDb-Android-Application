package com.ahmed.amdbmoviesapp.network

import com.ahmed.amdbmoviesapp.entities.AiringTodayPage
import com.ahmed.amdbmoviesapp.entities.MovieCreditsResponse
import com.ahmed.amdbmoviesapp.entities.MovieDetailsPage
import com.ahmed.amdbmoviesapp.entities.MoviePage
import com.ahmed.amdbmoviesapp.entities.MovieSearchPage
import com.ahmed.amdbmoviesapp.entities.MoviesTrailerPage
import com.ahmed.amdbmoviesapp.entities.OnTheAirTvsPage
import com.ahmed.amdbmoviesapp.entities.TvCreditsResponse
import com.ahmed.amdbmoviesapp.entities.TvDetailsPage
import com.ahmed.amdbmoviesapp.entities.TvPage
import com.ahmed.amdbmoviesapp.entities.TvSearchPage
import com.ahmed.amdbmoviesapp.entities.TvsTrailerPage
import com.ahmed.amdbmoviesapp.entities.UpComingMoviesPage
import retrofit2.http.GET
import retrofit2.Response
import retrofit2.http.Path
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


    @GET("movie/{movie_id}")
    fun getMovieDetails(@Path("movie_id") movieId: Int, @Query("language") language: String = "en-US"): Response<MovieDetailsPage>

    @GET("tv/{tv_id}")
    fun getSeriesDetails(@Path("tv_id") tvId: Int, @Query("language") language: String = "en-US"): Response<TvDetailsPage>

    @GET("search/movie")
    fun searchMovies(@Query("query") query: String, @Query("language") language: String = "en-US"): Response<MovieSearchPage>

    @GET("search/tv")
    fun searchSeries(@Query("query") query: String, @Query("language") language: String = "en-US"): Response<TvSearchPage>

    @GET("movie/{movie_id}/videos")
    fun getMovieTrailers(@Path("movie_id") movieId: Int, @Query("language") language: String = "en-US"): Response<MoviesTrailerPage>

    @GET("tv/{tv_id}/videos")
    fun getSeriesTrailers(@Path("tv_id") tvId: Int, @Query("language") language: String = "en-US"): Response<TvsTrailerPage>

    @GET("movie/upcoming")
    fun getUpcomingMovies(@Query("language") language: String = "en-US", @Query("region") region: String = "US"): Response<UpComingMoviesPage>

    @GET("tv/airing_today")
    fun getAiringTodaySeries(@Query("language") language: String = "en-US", @Query("region") region: String = "US"): Response<AiringTodayPage>

    @GET("tv/on_the_air")
    fun getOnTheAirSeries(@Query("language") language: String = "en-US", @Query("region") region: String = "US"): Response<OnTheAirTvsPage>

    @GET("movie/{movie_id}/credits")
    suspend fun getMovieCredits(@Path("movie_id") movieId: Int): Response<MovieCreditsResponse>

    @GET("tv/{tv_id}/credits")
    suspend fun getTvCredits(@Path("tv_id") movieId: Int): Response<TvCreditsResponse>


}