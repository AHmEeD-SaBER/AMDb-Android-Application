package com.ahmed.amdbmoviesapp.entities

data class TvSearchPage(
    val page: Int,
    val results: List<TvsResults>,
    val total_pages: Int,
    val total_results: Int
)