package com.fernandocejas.sample.di

import com.fernandocejas.sample.AndroidApplication
import com.fernandocejas.sample.features.movies.MovieDetailsFragment
import com.fernandocejas.sample.features.movies.MoviesFragment
import com.fernandocejas.sample.features.movies.MoviesModule
import com.fernandocejas.sample.navigation.RouteActivity
import dagger.Component
import javax.inject.Provider
import javax.inject.Singleton


@Singleton
@Component(modules = arrayOf(ApplicationModule::class, ApplicationBinders::class, MoviesModule::class))
interface ApplicationComponent {
    fun inject(application: AndroidApplication)
    fun inject(routeActivity: RouteActivity)

    //TODO: do scoping here
    fun inject(moviesFragment: MoviesFragment)
    fun inject(movieDetailsFragment: MovieDetailsFragment)

    fun subComponentBuilders(): Map<Class<*>, Provider<SubcomponentBuilder<*>>>
}
