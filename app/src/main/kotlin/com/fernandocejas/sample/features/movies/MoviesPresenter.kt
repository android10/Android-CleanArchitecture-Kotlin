package com.fernandocejas.sample.features.movies

import javax.inject.Inject

class MoviesPresenter @Inject constructor() {

    var moviesView: MoviesView? = null

    fun destroy() {
        //TODO: better approach for handling lifecycle
    }
}
