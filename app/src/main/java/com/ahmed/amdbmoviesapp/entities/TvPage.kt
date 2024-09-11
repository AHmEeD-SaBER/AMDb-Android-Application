package com.ahmed.amdbmoviesapp.entities

data class TvPage(
    val page: Int,
    val results: List<TvResponse>,
    val total_pages: Int,
    val total_results: Int
)