package com.fernandocejas.sample.features.movies

import android.content.Context

interface MoviesView {
    fun renderList(movies: List<MovieViewModel>)
    fun displayDetails(movie: MovieViewModel)

    //Data Loading: TODO -> extract this for reuse ability
    fun showLoading()
    fun hideLoading()

    //Error Handling: TODO -> extract this for reuse ability
    fun showError(message: String)
    fun showRetry()
    fun hideRetry()

    //Context: TODO -> extract this for reuse ability
    fun context(): Context
}
