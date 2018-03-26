package com.fernandocejas.sample.features.movies

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.View
import com.fernandocejas.sample.BaseFragment
import com.fernandocejas.sample.R
import com.fernandocejas.sample.navigation.Navigator
import kotlinx.android.synthetic.main.fragment_movies.movieList
import javax.inject.Inject

class MoviesFragment : BaseFragment() {

    @Inject lateinit var navigator: Navigator
    @Inject lateinit var moviesViewModelFactory: MoviesViewModel.Factory
    @Inject lateinit var moviesAdapter: MoviesAdapter

    private lateinit var moviesViewModel: MoviesViewModel

    override fun layoutId() = R.layout.fragment_movies

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)

        moviesViewModel = ViewModelProviders.of(this, moviesViewModelFactory).get(MoviesViewModel::class.java)
        moviesViewModel.movies.observe(this, Observer { moviesAdapter.collection = it.orEmpty() })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeView()
    }

    private fun initializeView() {
        movieList.layoutManager = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
        movieList.adapter = moviesAdapter
        moviesAdapter.clickListener = { movie, navigationExtras ->
                    navigator.showMovieDetails(activity!!, movie, navigationExtras) }
    }
}
