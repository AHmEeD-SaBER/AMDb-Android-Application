package com.ahmed.amdbmoviesapp.repository

import com.ahmed.amdbmoviesapp.datasources.NetworkDataSource

class RepositoryImplementation(val networkDataSource: NetworkDataSource) : Repository {
    override suspend fun getPopularMovies() = networkDataSource.getPopularMovies()
    override suspend fun getPopularSeries() = networkDataSource.getPopularSeries()
    override suspend fun getTopRatedMovies() = networkDataSource.getTopRatedMovies()
    override suspend fun getTopRatedSeries() = networkDataSource.getTopRatedSeries()
}