package com.kaungmaw.mymovie.domain

import com.squareup.moshi.Json

data class Movie(
    val id: Long,
    val posterPath: String,
    val title: String,
    val overview: String,
    val releaseDate: String,
    val popularity: String
)
