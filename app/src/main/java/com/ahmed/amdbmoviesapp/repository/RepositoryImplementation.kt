package com.ahmed.amdbmoviesapp.repository

import com.ahmed.amdbmoviesapp.datasources.NetworkDataSource

class RepositoryImplementation(val networkDataSource: NetworkDataSource) : Repository {
    override suspend fun getPopularMovies() = networkDataSource.getPopularMovies()
    override suspend fun getPopularSeries() = networkDataSource.getPopularSeries()
    override suspend fun getTopRatedMovies() = networkDataSource.getTopRatedMovies()
    override suspend fun getTopRatedSeries() = networkDataSource.getTopRatedSeries()
    override suspend fun getMovieDetails(movieId: Int) = networkDataSource.getMovieDetails(movieId)
    override suspend fun getSeriesDetails(tvId: Int) = networkDataSource.getSeriesDetails(tvId)
    override suspend fun searchMovies(query: String) = networkDataSource.searchMovies(query)
    override suspend fun searchSeries(query: String) = networkDataSource.searchSeries(query)
    override suspend fun getMovieTrailers(movieId: Int) = networkDataSource.getMovieTrailers(movieId)
    override suspend fun getSeriesTrailers(tvId: Int) = networkDataSource.getSeriesTrailers(tvId)
    override suspend fun getUpcomingMovies() = networkDataSource.getUpcomingMovies()
    override suspend fun getAiringTodaySeries() = networkDataSource.getAiringTodaySeries()
    override suspend fun getOnTheAirSeries() = networkDataSource.getOnTheAirSeries()
    override suspend fun getMovieCredits(movieId: Int) = networkDataSource.getMovieCredits(movieId)
    override suspend fun getTvCredits(tvId: Int) = networkDataSource.getTvCredits(tvId)
}