package com.ahmed.amdbmoviesapp.entities

data class MoviePage(
    val page: Int,
    val results: List<MoviesResponse>,
    val total_pages: Int,
    val total_results: Int
)