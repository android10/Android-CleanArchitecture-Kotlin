package com.fernandocejas.sample.features.movies

import com.fernandocejas.sample.framework.view.LoadingView

interface MovieDetailsModel : LoadingView {
    fun renderDetails(movie: MovieDetailsViewModel)
}
