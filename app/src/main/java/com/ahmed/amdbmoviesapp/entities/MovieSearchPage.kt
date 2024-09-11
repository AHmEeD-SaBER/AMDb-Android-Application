package com.ahmed.amdbmoviesapp.entities

data class MovieSearchPage(
    val page: Int,
    val results: List<MoviesResults>,
    val total_pages: Int,
    val total_results: Int
)