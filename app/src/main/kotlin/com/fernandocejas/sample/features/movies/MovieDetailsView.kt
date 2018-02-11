package com.fernandocejas.sample.features.movies

import com.fernandocejas.sample.framework.view.LoadingView

interface MovieDetailsView : LoadingView {
    fun renderDetails(movie: MovieDetailsViewModel)
}
