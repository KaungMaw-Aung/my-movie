package com.kaungmaw.mymovie.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.kaungmaw.mymovie.domain.Movie
import com.kaungmaw.mymovie.movies.MoviePagingSource
import com.kaungmaw.mymovie.network.MovieService
import kotlinx.coroutines.flow.Flow

private const val API_KEY = "948d1ec7e82027d36cf4ceb80ee7632c"

class MovieRepository {

    private val movieService = MovieService.movieServices

    fun getTrendingMovies(): Flow<PagingData<Movie>> {
        return Pager(PagingConfig(pageSize = 10)) {
            MoviePagingSource(movieService, API_KEY)
        }.flow
    }

}