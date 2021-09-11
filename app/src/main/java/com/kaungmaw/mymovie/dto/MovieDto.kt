package com.kaungmaw.mymovie.dto

import com.squareup.moshi.Json

data class MovieDto(
    val id: Long?,
    @field:Json(name = "poster_path")
    val posterPath: String?,
    val title: String?,
    val overview: String?,
    @field:Json(name = "release_date")
    val releaseDate: String?,
    val popularity: Double?
)
