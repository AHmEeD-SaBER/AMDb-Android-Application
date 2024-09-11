package com.ahmed.amdbmoviesapp.entities

data class UpComingMoviesPage(
    val dates: Dates,
    val page: Int,
    val results: List<UpComingMovies>,
    val total_pages: Int,
    val total_results: Int
)