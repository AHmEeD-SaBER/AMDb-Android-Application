package com.ahmed.amdbmoviesapp.datasources

import com.ahmed.amdbmoviesapp.entities.MoviePage
import com.ahmed.amdbmoviesapp.entities.TvPage
import retrofit2.Response

interface NetworkDataSource {
    suspend fun getPopularMovies(): Response<MoviePage>
    suspend fun getPopularSeries(): Response<TvPage>
    suspend fun getTopRatedMovies(): Response<MoviePage>
    suspend fun getTopRatedSeries(): Response<TvPage>
}