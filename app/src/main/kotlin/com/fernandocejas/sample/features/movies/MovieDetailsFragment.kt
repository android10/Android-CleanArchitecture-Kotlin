package com.fernandocejas.sample.features.movies

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.view.View
import com.fernandocejas.sample.BaseFragment
import com.fernandocejas.sample.R
import com.fernandocejas.sample.framework.extension.loadFromUrl
import com.fernandocejas.sample.framework.extension.loadUrlAndPostponeEnterTransition
import com.fernandocejas.sample.framework.extension.viewModel
import com.fernandocejas.sample.framework.extension.visible
import kotlinx.android.synthetic.main.fragment_movie_details.movieCast
import kotlinx.android.synthetic.main.fragment_movie_details.movieDetails
import kotlinx.android.synthetic.main.fragment_movie_details.movieDirector
import kotlinx.android.synthetic.main.fragment_movie_details.moviePlay
import kotlinx.android.synthetic.main.fragment_movie_details.moviePoster
import kotlinx.android.synthetic.main.fragment_movie_details.movieSummary
import kotlinx.android.synthetic.main.fragment_movie_details.movieYear
import kotlinx.android.synthetic.main.fragment_movie_details.scrollView
import kotlinx.android.synthetic.main.toolbar.toolbar
import javax.inject.Inject

class MovieDetailsFragment : BaseFragment() {

    companion object {
        private const val PARAM_MOVIE = "param_movie"

        fun forMovie(movie: MovieView): MovieDetailsFragment {
            val movieDetailsFragment = MovieDetailsFragment()
            val arguments = Bundle()
            arguments.putParcelable(PARAM_MOVIE, movie)
            movieDetailsFragment.arguments = arguments

            return movieDetailsFragment
        }
    }

    @Inject lateinit var movieDetailsViewModelFactory: MovieDetailsViewModel.Factory
    @Inject lateinit var movieDetailsAnimator: MovieDetailsAnimator

    private lateinit var movieDetailsViewModel: MovieDetailsViewModel

    override fun layoutId() = R.layout.fragment_movie_details

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)
        activity?.let { movieDetailsAnimator.postponeEnterTransition(it) }

        movieDetailsViewModel = viewModel(movieDetailsViewModelFactory)
        movieDetailsViewModel.movieDetails.observe(this, Observer { renderMovieDetails(it!!) })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (firstTimeCreated(savedInstanceState)) {
            movieDetailsViewModel.loadMovieDetails((arguments?.get(PARAM_MOVIE) as MovieView).id)
        } else {
            movieDetailsAnimator.scaleUpView(moviePlay)
            movieDetailsAnimator.cancelTransition(moviePoster)
            moviePoster.loadFromUrl((arguments!![PARAM_MOVIE] as MovieView).poster)
        }
    }

    override fun onBackPressed() {
        movieDetailsAnimator.fadeInvisible(scrollView, movieDetails)
        if (moviePlay.visible())
            movieDetailsAnimator.scaleDownView(moviePlay)
        else
            movieDetailsAnimator.cancelTransition(moviePoster)
    }

    private fun renderMovieDetails(movie: MovieDetailsView) {
        with(movie) {
            activity?.let {
                moviePoster.loadUrlAndPostponeEnterTransition(poster, it)
                it.toolbar.title = title
            }
            movieSummary.text = summary
            movieCast.text = cast
            movieDirector.text = director
            movieYear.text = year.toString()
            moviePlay.setOnClickListener { movieDetailsViewModel.playMovie(trailer) }
        }
        movieDetailsAnimator.fadeVisible(scrollView, movieDetails)
        movieDetailsAnimator.scaleUpView(moviePlay)
    }
}
