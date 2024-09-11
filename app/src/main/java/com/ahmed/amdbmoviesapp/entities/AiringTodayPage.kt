package com.ahmed.amdbmoviesapp.entities

data class AiringTodayPage(
    val page: Int,
    val results: List<AiringTodayTvs>,
    val total_pages: Int,
    val total_results: Int
)