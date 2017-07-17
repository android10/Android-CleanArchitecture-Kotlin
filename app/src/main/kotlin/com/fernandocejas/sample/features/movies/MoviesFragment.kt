package com.fernandocejas.sample.features.movies

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fernandocejas.sample.BaseFragment
import com.fernandocejas.sample.R
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

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.view(this)
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onDestroyView() {
        super.onDestroyView()
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

    override fun context(): Context {
        throw UnsupportedOperationException("not implemented")
    }
}
