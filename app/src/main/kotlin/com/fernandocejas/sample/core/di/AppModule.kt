package com.fernandocejas.sample.core.di

import com.fernandocejas.sample.core.navigation.Navigator
import com.fernandocejas.sample.core.platform.NetworkHandler
import com.fernandocejas.sample.features.login.Authenticator
import com.fernandocejas.sample.features.movies.GetMovieDetails
import com.fernandocejas.sample.features.movies.GetMovies
import com.fernandocejas.sample.features.movies.MovieDetailsAnimator
import com.fernandocejas.sample.features.movies.MovieDetailsViewModel
import com.fernandocejas.sample.features.movies.MoviesAdapter
import com.fernandocejas.sample.features.movies.MoviesRepository
import com.fernandocejas.sample.features.movies.MoviesService
import com.fernandocejas.sample.features.movies.MoviesViewModel
import com.fernandocejas.sample.features.movies.PlayMovie
import okhttp3.OkHttpClient
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {
    // Core
    singleOf(::retrofit)
    singleOf(::NetworkHandler)
    singleOf(::Navigator)

    // Login Feature
    singleOf(::Authenticator)

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

fun retrofit(): Retrofit {
    return Retrofit.Builder()
        .baseUrl("https://raw.githubusercontent.com/android10/Sample-Data/master/Android-CleanArchitecture-Kotlin/")
        .client(OkHttpClient.Builder().build())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}
