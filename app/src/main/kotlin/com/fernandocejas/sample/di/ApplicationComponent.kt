package com.fernandocejas.sample.di

import com.fernandocejas.sample.AndroidApplication
import com.fernandocejas.sample.di.viewmodel.ViewModelModule
import com.fernandocejas.sample.features.movies.MovieDetailsFragment
import com.fernandocejas.sample.features.movies.MoviesFragment
import com.fernandocejas.sample.navigation.RouteActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, ViewModelModule::class])
interface ApplicationComponent {
    fun inject(application: AndroidApplication)
    fun inject(routeActivity: RouteActivity)

    fun inject(moviesFragment: MoviesFragment)
    fun inject(movieDetailsFragment: MovieDetailsFragment)
}
