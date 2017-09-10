package com.fernandocejas.sample.features.movies

import android.os.Bundle
import android.view.View
import com.fernandocejas.sample.BaseFragment
import com.fernandocejas.sample.R
import com.fernandocejas.sample.framework.extension.loadUrlAndPostponeEnterTransition
import kotlinx.android.synthetic.main.fragment_movie_details.*


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
        activity.supportPostponeEnterTransition()
        appComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadMoviePoster()
    }

    private fun loadMoviePoster() {
        val movie = arguments[PARAM_MOVIE] as MovieViewModel
        moviePoster.loadUrlAndPostponeEnterTransition(movie.poster, activity)
    }
}
