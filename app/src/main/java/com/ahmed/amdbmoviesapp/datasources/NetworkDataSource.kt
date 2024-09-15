package com.ahmed.amdbmoviesapp.datasources

import com.ahmed.amdbmoviesapp.entities.AiringTodayPage
import com.ahmed.amdbmoviesapp.entities.MovieCreditsResponse
import com.ahmed.amdbmoviesapp.entities.MovieDetailsPage
import com.ahmed.amdbmoviesapp.entities.MoviePage
import com.ahmed.amdbmoviesapp.entities.MovieSearchPage
import com.ahmed.amdbmoviesapp.entities.MoviesTrailerPage
import com.ahmed.amdbmoviesapp.entities.OnAirTvResponse
import com.ahmed.amdbmoviesapp.entities.TvCreditsResponse
import com.ahmed.amdbmoviesapp.entities.TvDetailsPage
import com.ahmed.amdbmoviesapp.entities.TvPage
import com.ahmed.amdbmoviesapp.entities.TvSearchPage
import com.ahmed.amdbmoviesapp.entities.TvsTrailerPage
import com.ahmed.amdbmoviesapp.entities.UpcomingMovieResponse
import retrofit2.Response

interface NetworkDataSource {
    suspend fun getPopularMovies(): Response<MoviePage>
    suspend fun getPopularSeries(): Response<TvPage>
    suspend fun getUpcomingMovies(): Response<UpcomingMovieResponse>
    suspend fun getMovieDetails(movieId: Int) : Response<MovieDetailsPage>
    suspend fun getOnAirTvShows() : Response<OnAirTvResponse>
    suspend fun getSeriesDetails(tvId: Int) : Response<TvDetailsPage>
    suspend fun searchMovies(query: String) : Response<MovieSearchPage>
    suspend fun searchSeries(query: String) : Response<TvSearchPage>
    suspend fun getMovieTrailers(movieId: Int) : Response<MoviesTrailerPage>
    suspend fun getSeriesTrailers(tvId: Int) : Response<TvsTrailerPage>
    suspend fun getMovieCredits(movieId: Int) : Response<MovieCreditsResponse>
    suspend fun getTvCredits(tvId: Int) : Response<TvCreditsResponse>

}