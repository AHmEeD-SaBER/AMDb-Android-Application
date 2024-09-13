package com.ahmed.amdbmoviesapp.datasources

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
import com.ahmed.amdbmoviesapp.network.RetrofitClient
import retrofit2.Response

object NetworkDSImplementaion : NetworkDataSource {
    override suspend fun getPopularMovies(): Response<MoviePage> {
        return RetrofitClient.retrofitService.getPopularMovies()
    }

    override suspend fun getPopularSeries(): Response<TvPage> {
        return RetrofitClient.retrofitService.getPopularSeries()
    }

    override suspend fun getTopRatedMovies(): Response<MoviePage> {
        return RetrofitClient.retrofitService.getTopRatedMovies()
    }

    override suspend fun getTopRatedSeries(): Response<TvPage> {
        return RetrofitClient.retrofitService.getTopRatedSeries()
    }
    override suspend fun getMovieDetails(movieId: Int): Response<MovieDetailsPage> {
        return RetrofitClient.retrofitService.getMovieDetails(movieId, "en-US")
    }

    override suspend fun getSeriesDetails(tvId: Int): Response<TvDetailsPage> {
        return RetrofitClient.retrofitService.getSeriesDetails(tvId, "en-US")
    }

    override suspend fun searchMovies(query: String): Response<MovieSearchPage> {
        return RetrofitClient.retrofitService.searchMovies(query, "en-US")
    }

    override suspend fun searchSeries(query: String): Response<TvSearchPage> {
        return RetrofitClient.retrofitService.searchSeries(query, "en-US")
    }

    override suspend fun getMovieTrailers(movieId: Int): Response<MoviesTrailerPage> {
        return RetrofitClient.retrofitService.getMovieTrailers(movieId, "en-US")
    }

    override suspend fun getSeriesTrailers(tvId: Int): Response<TvsTrailerPage> {
        return RetrofitClient.retrofitService.getSeriesTrailers(tvId, "en-US")
    }

    override suspend fun getUpcomingMovies(): Response<UpComingMoviesPage> {
        return RetrofitClient.retrofitService.getUpcomingMovies("en-US", "US")
    }

    override suspend fun getAiringTodaySeries(): Response<AiringTodayPage> {
        return RetrofitClient.retrofitService.getAiringTodaySeries("en-US", "US")
    }

    override suspend fun getOnTheAirSeries(): Response<OnTheAirTvsPage> {
        return RetrofitClient.retrofitService.getOnTheAirSeries("en-US", "US")
    }

    override suspend fun getMovieCredits(movieId: Int): Response<MovieCreditsResponse> {
        return RetrofitClient.retrofitService.getMovieCredits(movieId)
    }

    override suspend fun getTvCredits(tvId: Int): Response<TvCreditsResponse> {
        return RetrofitClient.retrofitService.getTvCredits(tvId)
    }



}