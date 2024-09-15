package com.ahmed.amdbmoviesapp.entities

data class UpcomingMovieResponse(
    val dates: Dates,
    val page: Int,
    val results: List<UpcomingMovie>,
    val total_pages: Int,
    val total_results: Int
)