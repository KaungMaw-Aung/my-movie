package com.kaungmaw.mymovie.movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.kaungmaw.mymovie.domain.Movie
import com.kaungmaw.mymovie.repositories.MovieRepository
import kotlinx.coroutines.flow.Flow

class MoviesViewModel : ViewModel() {

    private val movieRepo = MovieRepository()

    var moviesFlow: Flow<PagingData<Movie>>? = null

    private fun getMovies() {
        moviesFlow = movieRepo.getTrendingMovies().cachedIn(viewModelScope)
    }

    init {
        getMovies()
    }

}