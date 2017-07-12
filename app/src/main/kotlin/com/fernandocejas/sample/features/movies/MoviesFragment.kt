package com.fernandocejas.sample.features.movies

import com.fernandocejas.sample.BaseFragment
import com.fernandocejas.sample.R

class MoviesFragment : BaseFragment(), MoviesView {

    override fun layoutId(): Int {
        return R.layout.fragment_movies
    }

    override fun renderList(movies: List<MovieViewModel>) {
    }

    override fun displayDetails(movie: MovieViewModel) {
    }
}
