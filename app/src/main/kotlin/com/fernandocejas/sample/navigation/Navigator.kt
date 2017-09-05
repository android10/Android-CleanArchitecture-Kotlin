package com.fernandocejas.sample.navigation

import android.content.Context
import com.fernandocejas.sample.features.login.Authenticator
import com.fernandocejas.sample.features.login.LoginActivity
import com.fernandocejas.sample.features.movies.MovieDetailsActivity
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

    fun showMovieDetails(context: Context, movieId: Int) =
            context.startActivity(MovieDetailsActivity.callingIntent(context, movieId))

    private fun showLogin(context: Context) = context.startActivity(LoginActivity.callingIntent(context))
    private fun showMovies(context: Context) = context.startActivity(MoviesActivity.callingIntent(context))
}

