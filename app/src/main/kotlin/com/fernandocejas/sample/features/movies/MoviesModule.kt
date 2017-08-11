package com.fernandocejas.sample.features.movies

import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class MoviesModule {
    @Provides @Singleton fun provideMoviesRepository(repository: MoviesRepository.Source): MoviesRepository = repository
}
