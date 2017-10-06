package com.fernandocejas.sample.features.movies

import android.os.Bundle
import android.support.v4.view.animation.FastOutSlowInInterpolator
import android.transition.Fade
import android.transition.TransitionManager
import android.view.View
import com.fernandocejas.sample.BaseFragment
import com.fernandocejas.sample.R
import com.fernandocejas.sample.framework.extension.cancelTransition
import com.fernandocejas.sample.framework.extension.loadFromUrl
import com.fernandocejas.sample.framework.extension.loadUrlAndPostponeEnterTransition
import kotlinx.android.synthetic.main.fragment_movie_details.*
import kotlinx.android.synthetic.main.toolbar.*
import javax.inject.Inject

class MovieDetailsFragment : BaseFragment(), MovieDetailsView {
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

    @Inject lateinit var movieDetailsPresenter: MovieDetailsPresenter

    override fun layoutId() = R.layout.fragment_movie_details

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity.supportPostponeEnterTransition()
        appComponent.inject(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        movieDetailsPresenter.destroy()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (savedInstanceState == null) {
            initializeView()
            loadMovieDetails()
        } else {
            moviePlay.animate().scaleX(1.0F).scaleY(1.0F).setDuration(200)
                    .setInterpolator(FastOutSlowInInterpolator()).withLayer().setListener(null).start()
            moviePoster.loadFromUrl((arguments[PARAM_MOVIE] as MovieViewModel).poster)
            moviePoster.cancelTransition()
        }
    }

    override fun onBackPressed() {
        val transition = Fade()
        transition.startDelay = 200
        transition.duration = 400
        TransitionManager.beginDelayedTransition(scrollView, transition)
        movieDetails.visibility = View.INVISIBLE

        if (moviePlay.visibility == View.INVISIBLE) {
            moviePoster.cancelTransition()
        } else {
            moviePlay.animate().scaleX(0.0F).scaleY(0.0F).setDuration(200)
                    .setInterpolator(FastOutSlowInInterpolator()).withLayer().setListener(null).start()
        }
    }

    override fun renderDetails(movie: MovieDetailsViewModel) {
        val transition = Fade()
        transition.startDelay = 200
        transition.duration = 400
        TransitionManager.beginDelayedTransition(scrollView, transition)

        with(movie) {
            moviePoster.loadUrlAndPostponeEnterTransition(poster, activity)
            activity.toolbar.title = title
            movieSummary.text = summary
            movieCast.text = cast
            movieDirector.text = director
            movieYear.text = year.toString()
        }

        movieDetails.visibility = View.VISIBLE
        moviePlay.animate().scaleX(1.0F).scaleY(1.0F).setDuration(700)
                .setInterpolator(FastOutSlowInInterpolator()).withLayer().setListener(null).start()
    }

    override fun showLoading() {
        //TODO: implement method
    }

    override fun hideLoading() {
        //TODO: implement method
    }

    override fun dispose() {
        //TODO: dispose view resources
    }

    private fun initializeView() {
        movieDetailsPresenter.movieDetailsView = this
    }

    private fun loadMovieDetails() {
        movieDetailsPresenter.loadMovieDetails((arguments[PARAM_MOVIE] as MovieViewModel).id)
    }
}
