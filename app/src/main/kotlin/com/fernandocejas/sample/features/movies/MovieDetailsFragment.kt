package com.fernandocejas.sample.features.movies

import android.os.Bundle
import com.fernandocejas.sample.BaseFragment
import com.fernandocejas.sample.R

class MovieDetailsFragment : BaseFragment() {

    companion object {
        private const val PARAM_MOVIE = "param_movie"

        fun forMovie(movie: MovieViewModel): MovieDetailsFragment {
            val movieDetailsFragment = MovieDetailsFragment()
            val arguments = Bundle()
            arguments.putParcelable(PARAM_MOVIE, movie)
            movieDetailsFragment.arguments = arguments

            return movieDetailsFragment
        }
    }

    override fun layoutId() = R.layout.fragment_movie_details

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)
    }
}
