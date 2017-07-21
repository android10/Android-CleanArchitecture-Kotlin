package com.fernandocejas.sample.features.movies

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
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
        adapter.movies = movies
    }

    override fun displayDetails(movie: MovieViewModel) {
        throw NotImplementedError("not implemented")
    }

    private fun initializeView() {
        rv_movies.layoutManager = LinearLayoutManager(this.activity.application)
        rv_movies.adapter = adapter
        presenter.view(this)
    }
}
