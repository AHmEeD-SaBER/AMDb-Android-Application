package com.ahmed.amdbmoviesapp.entities

data class OnAirTvResponse(
    val page: Int,
    val results: List<OnAirTv>,
    val total_pages: Int,
    val total_results: Int
)