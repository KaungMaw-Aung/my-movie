package com.kaungmaw.mymovie.movies

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.kaungmaw.mymovie.R
import com.kaungmaw.mymovie.databinding.FragmentMoviesBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MoviesFragment : Fragment() {

    private lateinit var binding: FragmentMoviesBinding
    private val viewModel by viewModels<MoviesViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentMoviesBinding.inflate(inflater, container, false)
            .also { binding = it }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = MoviesAdapter()

        adapter.addLoadStateListener { loadState ->
            binding.rvMovies.isVisible = loadState.source.refresh is LoadState.NotLoading
            if (loadState.source.refresh is LoadState.Loading) {
                binding.lavLoadState.visibility = View.VISIBLE
                binding.lavLoadState.playAnimation()
            } else {
                binding.lavLoadState.cancelAnimation()
                binding.lavLoadState.visibility = View.INVISIBLE
            }
        }

        binding.rvMovies.adapter = adapter

        lifecycleScope.launch {
            viewModel.moviesFlow?.collectLatest { pagingData ->
                adapter.submitData(pagingData)
            }
        }

    }

}