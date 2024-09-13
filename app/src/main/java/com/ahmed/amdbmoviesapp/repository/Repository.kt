package com.ahmed.amdbmoviesapp.repository

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
import retrofit2.Response

interface Repository {
    suspend fun getPopularMovies(): Response<MoviePage>
    suspend fun getPopularSeries(): Response<TvPage>
    suspend fun getTopRatedMovies(): Response<MoviePage>
    suspend fun getTopRatedSeries(): Response<TvPage>
    suspend fun getMovieDetails(movieId: Int) : Response<MovieDetailsPage>
    suspend fun getSeriesDetails(tvId: Int) : Response<TvDetailsPage>
    suspend fun searchMovies(query: String) : Response<MovieSearchPage>
    suspend fun searchSeries(query: String) : Response<TvSearchPage>
    suspend fun getMovieTrailers(movieId: Int) : Response<MoviesTrailerPage>
    suspend fun getSeriesTrailers(tvId: Int) : Response<TvsTrailerPage>
    suspend fun getUpcomingMovies() : Response<UpComingMoviesPage>
    suspend fun getAiringTodaySeries() : Response<AiringTodayPage>
    suspend fun getOnTheAirSeries() : Response<OnTheAirTvsPage>
    suspend fun getMovieCredits(movieId: Int) : Response<MovieCreditsResponse>
    suspend fun getTvCredits(tvId: Int) : Response<TvCreditsResponse>

}