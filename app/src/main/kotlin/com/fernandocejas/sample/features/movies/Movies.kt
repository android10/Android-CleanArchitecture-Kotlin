package com.fernandocejas.sample.features.movies

import com.fernandocejas.sample.core.Feature
import com.fernandocejas.sample.features.movies.data.MoviesRepository
import com.fernandocejas.sample.features.movies.data.MoviesService
import com.fernandocejas.sample.features.movies.interactor.GetMovieDetails
import com.fernandocejas.sample.features.movies.interactor.GetMovies
import com.fernandocejas.sample.features.movies.interactor.PlayMovie
import com.fernandocejas.sample.features.movies.ui.MovieDetailsAnimator
import com.fernandocejas.sample.features.movies.ui.MovieDetailsViewModel
import com.fernandocejas.sample.features.movies.ui.MoviesAdapter
import com.fernandocejas.sample.features.movies.ui.MoviesViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module

fun moviesFeature() = object : Feature {

    override fun name() = "movies"

    override fun diModule() = module {
        // Movies Feature
        factoryOf(::MoviesService)
        factory { MoviesRepository.Network(get(), get()) } bind MoviesRepository::class
        // Movies
        viewModelOf(::MoviesViewModel)
        factoryOf(::GetMovies)
        factoryOf(::MoviesAdapter)
        // Movie Details
        viewModelOf(::MovieDetailsViewModel)
        factoryOf(::GetMovieDetails)
        factoryOf(::PlayMovie)
        factoryOf(::MovieDetailsAnimator)
    }
}
