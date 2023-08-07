package com.fernandocejas.sample.features.movies

import com.fernandocejas.sample.core.Feature
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
