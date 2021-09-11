package com.kaungmaw.mymovie.dto

import com.kaungmaw.mymovie.domain.Movie
import com.squareup.moshi.Json

data class MovieDto(
    val id: Long?,
    val poster_path: String?,
    val title: String?,
    val overview: String?,
    val release_date: String?,
    val popularity: Double?
)

fun MovieDto.asDomain(): Movie {
    return Movie(
        id = id ?: -1,
        posterPath = poster_path.orEmpty(),
        title = title.orEmpty(),
        overview = overview.orEmpty(),
        releaseDate = release_date.orEmpty(),
        popularity = popularity?.toString().orEmpty()
    )
}