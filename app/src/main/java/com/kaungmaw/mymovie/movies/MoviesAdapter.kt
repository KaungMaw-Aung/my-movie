package com.kaungmaw.mymovie.movies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.kaungmaw.mymovie.databinding.ItemRecyclerMovieBinding
import com.kaungmaw.mymovie.domain.Movie
import com.kaungmaw.mymovie.loadImageUrl

class MoviesAdapter : PagingDataAdapter<Movie, MovieViewHolder>(MoviesDiffUtil) {
    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(
            ItemRecyclerMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }
}

object MoviesDiffUtil : DiffUtil.ItemCallback<Movie>() {
    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem == newItem
    }
}

class MovieViewHolder(
    private val binding: ItemRecyclerMovieBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(data: Movie?) {
        binding.tvMovieTitle.text = data?.title ?: "..."
        binding.tvMovieOverview.text = data?.overview ?: "..."
        binding.tvReleasedDate.text = data?.releaseDate ?: "..."
        binding.tvPopularity.text = data?.popularity ?: "..."

        data?.posterPath?.let {
            loadImageUrl(binding.ivMoviePoster, it)
        }
    }

}