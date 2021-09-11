package com.kaungmaw.mymovie.dto

data class MovieResponseDto(
    val page: Int?,
    val results: List<MovieDto>?,
    val totalPages: Int?,
    val totalResults: Int?
)
