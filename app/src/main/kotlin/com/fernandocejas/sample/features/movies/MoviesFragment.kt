package com.fernandocejas.sample.features.movies

import android.os.Bundle
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.View
import com.fernandocejas.sample.BaseFragment
import com.fernandocejas.sample.R
import com.fernandocejas.sample.navigation.Navigator
import kotlinx.android.synthetic.main.fragment_movies.*
import javax.inject.Inject

class MoviesFragment : BaseFragment(), MoviesView {

    @Inject lateinit var navigator: Navigator
    @Inject lateinit var moviesPresenter: MoviesPresenter
    @Inject lateinit var moviesAdapter: MoviesAdapter

    override fun layoutId() = R.layout.fragment_movies

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeView()
        if (firstTimeCreated(savedInstanceState)) { initializeView(); loadMovies() }
    }

    override fun renderList(movies: List<MovieViewModel>) {
        moviesAdapter.collection = movies
    }

    override fun displayDetails(movie: MovieViewModel, navigationExtras: Navigator.Extras) {
        activity?.let { navigator.showMovieDetails(it, movie, navigationExtras) }
    }

    override fun showLoading() {
        //TODO: implement method
    }

    override fun hideLoading() {
        //TODO: implement method
    }

    private fun initializeView() {
        movieList.layoutManager = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
        moviesAdapter.clickListener =
                { movieViewModel, navigationExtras ->  moviesPresenter.onMovieClick(movieViewModel, navigationExtras) }
        movieList.adapter = moviesAdapter
        moviesPresenter.moviesView = this
    }

    private fun loadMovies() = moviesPresenter.loadMovies()
}
