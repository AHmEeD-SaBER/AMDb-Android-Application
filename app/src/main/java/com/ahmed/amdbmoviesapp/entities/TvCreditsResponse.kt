package com.ahmed.amdbmoviesapp.entities

data class TvCreditsResponse(
    val cast: List<CastX>,
    val crew: List<Crew>,
    val id: Int
)