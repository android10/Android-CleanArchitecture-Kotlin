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
import androidx.annotation.StringRes
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.fernandocejas.sample.R
import com.fernandocejas.sample.core.failure.Failure
import com.fernandocejas.sample.core.failure.Failure.NetworkConnection
import com.fernandocejas.sample.core.failure.Failure.ServerError
import com.fernandocejas.sample.core.extension.failure
import com.fernandocejas.sample.core.extension.invisible
import com.fernandocejas.sample.core.extension.observe
import com.fernandocejas.sample.core.extension.visible
import com.fernandocejas.sample.core.navigation.Navigator
import com.fernandocejas.sample.core.platform.BaseFragment
import com.fernandocejas.sample.databinding.FragmentMoviesBinding
import com.fernandocejas.sample.features.movies.failure.MovieFailure.ListNotAvailable
import org.koin.android.ext.android.inject

class MoviesFragment : BaseFragment() {

    private val navigator: Navigator by inject()
    private val moviesAdapter: MoviesAdapter by inject()

    private val moviesViewModel: MoviesViewModel by inject()

    private var _binding: FragmentMoviesBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        with(moviesViewModel) {
            observe(movies, ::renderMoviesList)
            failure(failure, ::handleFailure)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMoviesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeView()
        loadMoviesList()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initializeView() {
        binding.movieList.layoutManager = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
        binding.movieList.adapter = moviesAdapter
        moviesAdapter.clickListener = { movie, navigationExtras ->
            navigator.showMovieDetails(requireActivity(), movie, navigationExtras)
        }
    }

    private fun loadMoviesList() {
        binding.emptyView.invisible()
        binding.movieList.visible()
        showProgress()
        moviesViewModel.loadMovies()
    }

    private fun renderMoviesList(movies: List<MovieView>?) {
        moviesAdapter.collection = movies.orEmpty()
        hideProgress()
    }

    private fun handleFailure(failure: Failure?) {
        when (failure) {
            is NetworkConnection -> renderFailure(R.string.failure_network_connection)
            is ServerError -> renderFailure(R.string.failure_server_error)
            is ListNotAvailable -> renderFailure(R.string.failure_movies_list_unavailable)
            else -> renderFailure(R.string.failure_server_error)
        }
    }

    private fun renderFailure(@StringRes message: Int) {
        binding.movieList.invisible()
        binding.emptyView.visible()
        hideProgress()
        notifyWithAction(message, R.string.action_refresh, ::loadMoviesList)
    }
}
