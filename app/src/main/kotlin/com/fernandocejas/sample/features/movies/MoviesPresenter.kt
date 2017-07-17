package com.fernandocejas.sample.features.movies

import javax.inject.Inject

class MoviesPresenter @Inject constructor() {

    private lateinit var view: MoviesView

    fun view(moviesView: MoviesView) {
        view = moviesView
    }

    fun destroy() {
        //TODO: better approach for handling lifecycle
    }
}
