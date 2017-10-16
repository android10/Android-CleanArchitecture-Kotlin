package com.fernandocejas.sample.features.movies

import android.os.Bundle
import android.view.View
import com.fernandocejas.sample.BaseFragment
import com.fernandocejas.sample.R
import com.fernandocejas.sample.framework.extension.loadFromUrl
import com.fernandocejas.sample.framework.extension.loadUrlAndPostponeEnterTransition
import com.fernandocejas.sample.framework.extension.visible
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
    @Inject lateinit var movieDetailsAnimator: MovieDetailsAnimator

    override fun layoutId() = R.layout.fragment_movie_details

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)
        movieDetailsAnimator.postponeEnterTransition(activity)
    }

    override fun onDestroy() {
        super.onDestroy()
        movieDetailsPresenter.destroy()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (firstTimeCreated(savedInstanceState)) {
            initializeView()
            loadMovieDetails() }
        else {
            movieDetailsAnimator.scaleUpView(moviePlay)
            movieDetailsAnimator.cancelTransition(moviePoster)
            moviePoster.loadFromUrl((arguments[PARAM_MOVIE] as MovieViewModel).poster)
        }
    }

    override fun onBackPressed() {
        movieDetailsAnimator.fadeInvisible(scrollView, movieDetails)
        if (moviePlay.visible())
            movieDetailsAnimator.scaleDownView(moviePlay)
        else
            movieDetailsAnimator.cancelTransition(moviePoster)
    }

    override fun renderDetails(movie: MovieDetailsViewModel) {
        with(movie) {
            moviePoster.loadUrlAndPostponeEnterTransition(poster, activity)
            activity.toolbar.title = title
            movieSummary.text = summary
            movieCast.text = cast
            movieDirector.text = director
            movieYear.text = year.toString()
            moviePlay.setOnClickListener { movieDetailsPresenter.playMovie(activity, trailer) }
        }
        movieDetailsAnimator.fadeVisible(scrollView, movieDetails)
        movieDetailsAnimator.scaleUpView(moviePlay)
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
