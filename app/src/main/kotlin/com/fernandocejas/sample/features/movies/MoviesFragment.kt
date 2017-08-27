package com.fernandocejas.sample.features.movies

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.fernandocejas.sample.BaseFragment
import com.fernandocejas.sample.R
import kotlinx.android.synthetic.main.fragment_movies.*
import javax.inject.Inject

class MoviesFragment : BaseFragment(), MoviesView {

    @Inject lateinit var moviesPresenter: MoviesPresenter
    @Inject lateinit var moviesAdapter: MoviesAdapter

    override fun layoutId(): Int = R.layout.fragment_movies

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeView()
        savedInstanceState ?: loadMovies()
    }

    override fun onDestroy() {
        moviesPresenter.destroy()
        super.onDestroy()
    }

    override fun renderList(movies: List<MovieViewModel>) {
        moviesAdapter.collection = movies
    }

    override fun displayDetails(movie: MovieViewModel) {
        TODO()
    }

    private fun initializeView() {
        rv_movies.layoutManager = LinearLayoutManager(this.activity.application)
        rv_movies.adapter = moviesAdapter
        moviesPresenter.initialize(this)
    }

    private fun loadMovies() {
        moviesPresenter.loadMovies()
    }
}
