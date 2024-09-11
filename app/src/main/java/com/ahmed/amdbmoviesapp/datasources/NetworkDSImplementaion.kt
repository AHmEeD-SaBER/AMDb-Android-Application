package com.ahmed.amdbmoviesapp.datasources

import com.ahmed.amdbmoviesapp.entities.MoviePage
import com.ahmed.amdbmoviesapp.entities.TvPage
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
}