package com.kaungmaw.mymovie.movies

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.kaungmaw.mymovie.domain.Movie
import com.kaungmaw.mymovie.dto.asDomain
import com.kaungmaw.mymovie.network.MovieServices
import retrofit2.HttpException
import java.io.IOException

class MoviePagingSource(
    private val movieServices: MovieServices,
    private val apiKey: String
) : PagingSource<Int, Movie>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        return try {
            val currentPageNumber = params.key ?: 1
            val response = movieServices.getTrendingMovies(
                apiKey = apiKey,
                page = currentPageNumber
            )
            LoadResult.Page(
                data = response.body()?.results?.map { it.asDomain() }.orEmpty(),
                prevKey = null,
                nextKey = if (currentPageNumber == response.body()?.totalPages) null else currentPageNumber + 1
            )
        } catch (e: IOException) {
            return LoadResult.Error(e)
        } catch (e: HttpException) {
            return LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }

}