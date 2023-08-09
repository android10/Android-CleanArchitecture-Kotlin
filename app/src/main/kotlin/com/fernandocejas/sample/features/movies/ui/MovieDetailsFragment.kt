/**
 * Copyright (C) 2020 Fernando Cejas Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.fernandocejas.sample.features.movies.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import com.fernandocejas.sample.R
import com.fernandocejas.sample.core.failure.Failure
import com.fernandocejas.sample.core.failure.Failure.NetworkConnection
import com.fernandocejas.sample.core.failure.Failure.ServerError
import com.fernandocejas.sample.core.extension.close
import com.fernandocejas.sample.core.extension.failure
import com.fernandocejas.sample.core.extension.isVisible
import com.fernandocejas.sample.core.extension.loadFromUrl
import com.fernandocejas.sample.core.extension.loadUrlAndPostponeEnterTransition
import com.fernandocejas.sample.core.extension.observe
import com.fernandocejas.sample.core.platform.BaseActivity
import com.fernandocejas.sample.core.platform.BaseFragment
import com.fernandocejas.sample.databinding.FragmentMovieDetailsBinding
import com.fernandocejas.sample.features.movies.failure.MovieFailure.NonExistentMovie
import org.koin.android.ext.android.inject

class MovieDetailsFragment : BaseFragment() {

    companion object {
        private const val PARAM_MOVIE = "param_movie"

        fun forMovie(movie: MovieView?) = MovieDetailsFragment().apply {
            arguments = bundleOf(PARAM_MOVIE to movie)
        }
    }

    private val movieDetailsAnimator: MovieDetailsAnimator by inject()

    private val movieDetailsViewModel: MovieDetailsViewModel by inject()

    private var _binding: FragmentMovieDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.let { movieDetailsAnimator.postponeEnterTransition(it) }

        with(movieDetailsViewModel) {
            observe(movieDetails, ::renderMovieDetails)
            failure(failure, ::handleFailure)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (firstTimeCreated(savedInstanceState)) {
            movieDetailsViewModel.loadMovieDetails((arguments?.get(PARAM_MOVIE) as MovieView).id)
        } else {
            movieDetailsAnimator.scaleUpView(binding.moviePlay)
            movieDetailsAnimator.cancelTransition(binding.moviePoster)
            binding.moviePoster.loadFromUrl((requireArguments()[PARAM_MOVIE] as MovieView).poster)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onBackPressed() {
        movieDetailsAnimator.fadeInvisible(binding.scrollView, binding.movieDetails)
        if (binding.moviePlay.isVisible())
            movieDetailsAnimator.scaleDownView(binding.moviePlay)
        else
            movieDetailsAnimator.cancelTransition(binding.moviePoster)
    }

    private fun renderMovieDetails(movie: MovieDetailsView?) {
        movie?.let {
            with(movie) {
                activity?.let {
                    binding.moviePoster.loadUrlAndPostponeEnterTransition(poster, it)
                    (it as BaseActivity).toolbar().title = title
                }
                with(binding) {
                    movieSummary.text = summary
                    movieCast.text = cast
                    movieDirector.text = director
                    movieYear.text = year.toString()
                    moviePlay.setOnClickListener { movieDetailsViewModel.playMovie(trailer) }
                }
            }
        }
        movieDetailsAnimator.fadeVisible(binding.scrollView, binding.movieDetails)
        movieDetailsAnimator.scaleUpView(binding.moviePlay)
    }

    private fun handleFailure(failure: Failure?) {
        when (failure) {
            is NetworkConnection -> {
                notify(R.string.failure_network_connection); close()
            }
            is ServerError -> {
                notify(R.string.failure_server_error); close()
            }
            is NonExistentMovie -> {
                notify(R.string.failure_movie_non_existent); close()
            }
            else -> {
                notify(R.string.failure_server_error); close()
            }
        }
    }
}
