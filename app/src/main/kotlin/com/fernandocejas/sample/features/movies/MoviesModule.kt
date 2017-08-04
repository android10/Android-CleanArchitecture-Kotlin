package com.fernandocejas.sample.features.movies

import com.fernandocejas.sample.framework.executor.ExecutionScheduler
import com.fernandocejas.sample.framework.executor.ThreadScheduler
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class MoviesModule {
    @Provides @Singleton fun provideMoviesRepository(repository: MoviesRepository.Source): MoviesRepository = repository
}
