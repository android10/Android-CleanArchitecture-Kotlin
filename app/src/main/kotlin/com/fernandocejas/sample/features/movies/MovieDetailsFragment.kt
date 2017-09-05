package com.fernandocejas.sample.features.movies

import android.os.Bundle
import android.view.View
import com.fernandocejas.sample.BaseFragment
import com.fernandocejas.sample.R
import kotlinx.android.synthetic.main.fragment_movie_details.*

class MovieDetailsFragment : BaseFragment() {

    companion object {
        private const val PARAM_MOVIE_ID = "param_movie_id"

        fun forMovie(movieId: Int): MovieDetailsFragment {
            val movieDetailsFragment = MovieDetailsFragment()
            val arguments = Bundle()
            arguments.putInt(PARAM_MOVIE_ID, movieId)
            movieDetailsFragment.arguments = arguments

            return movieDetailsFragment
        }
    }

    override fun layoutId() = R.layout.fragment_movie_details

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        movieId.text = arguments.getInt(PARAM_MOVIE_ID).toString()
    }
}
