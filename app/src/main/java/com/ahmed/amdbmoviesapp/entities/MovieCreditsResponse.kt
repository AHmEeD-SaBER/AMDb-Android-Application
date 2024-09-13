package com.ahmed.amdbmoviesapp.entities

data class MovieCreditsResponse(
    val cast: List<Cast>,
    val crew: List<Crew>,
    val id: Int
)