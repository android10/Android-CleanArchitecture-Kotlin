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

    interface MoviesListener {
        fun onMovieClicked(movie: MovieViewModel)
    }

    @Inject lateinit var presenter: MoviesPresenter
    @Inject lateinit var adapter: MoviesAdapter

    override fun layoutId(): Int {
        return R.layout.fragment_movies
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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
    }

    override fun onDetach() {
        super.onDetach()
    }

    override fun renderList(movies: List<MovieViewModel>) {
    }

    override fun displayDetails(movie: MovieViewModel) {
    }
}
