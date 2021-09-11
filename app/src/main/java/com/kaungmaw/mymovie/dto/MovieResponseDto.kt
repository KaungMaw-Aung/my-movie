package com.kaungmaw.mymovie.dto

data class MovieResponseDto(
    val page: Long?,
    val results: List<MovieDto>?,
    val totalPages: Long?,
    val totalResults: Long?
)
