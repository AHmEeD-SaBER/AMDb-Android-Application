package com.ahmed.amdbmoviesapp.entities

data class OnTheAirTvsPage(
    val page: Int,
    val results: List<OnTheAirTvs>,
    val total_pages: Int,
    val total_results: Int
)