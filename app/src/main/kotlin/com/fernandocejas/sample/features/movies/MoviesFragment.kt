package com.fernandocejas.sample.features.movies

import android.os.Bundle
import android.view.View
import com.fernandocejas.sample.BaseFragment
import com.fernandocejas.sample.R
import kotlinx.android.synthetic.main.fragment_movies.*
import javax.inject.Inject

class MoviesFragment : BaseFragment(), MoviesView {

    @Inject lateinit var presenter: MoviesPresenter
    @Inject lateinit var adapter: MoviesAdapter

    override fun layoutId(): Int {
        return R.layout.fragment_movies
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeView()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.destroy()
    }

    override fun renderList(movies: List<MovieViewModel>) {
    }

    override fun displayDetails(movie: MovieViewModel) {
    }

    override fun showLoading() {
    }

    override fun hideLoading() {
    }

    override fun showError(message: String) {
    }

    override fun showRetry() {
    }

    override fun hideRetry() {
    }

    private fun initializeView() {
        rv_movies.layoutManager = MoviesLayoutManager(this.activity.application)
        rv_movies.adapter = adapter
        presenter.view(this)
    }
}
