package com.fernandocejas.sample.navigation

import android.content.Context
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.app.FragmentActivity
import android.view.View
import android.widget.ImageView
import com.fernandocejas.sample.features.login.Authenticator
import com.fernandocejas.sample.features.login.LoginActivity
import com.fernandocejas.sample.features.movies.MovieDetailsActivity
import com.fernandocejas.sample.features.movies.MovieViewModel
import com.fernandocejas.sample.features.movies.MoviesActivity
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class Navigator
@Inject constructor(private val authenticator: Authenticator) {

    fun showMain(context: Context) {
        when (authenticator.userLoggedIn()) {
            true -> showMovies(context)
            false -> showLogin(context)
        }
    }

    fun showMovieDetails(activity: FragmentActivity, movie: MovieViewModel, navigationExtras: Extras) {
        val intent = MovieDetailsActivity.callingIntent(activity, movie)
        val sharedView = navigationExtras.transitionSharedElement as ImageView
        val activityOptions = ActivityOptionsCompat
                .makeSceneTransitionAnimation(activity, sharedView, sharedView.transitionName)
        activity.startActivity(intent, activityOptions.toBundle())
    }

    private fun showLogin(context: Context) = context.startActivity(LoginActivity.callingIntent(context))
    private fun showMovies(context: Context) = context.startActivity(MoviesActivity.callingIntent(context))

    class Extras(val transitionSharedElement: View)
}


