package com.fernandocejas.sample.di

import android.content.Context
import com.fernandocejas.sample.AndroidApplication
import com.fernandocejas.sample.framework.executor.ExecutionScheduler
import com.fernandocejas.sample.framework.executor.ThreadScheduler
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule(private val application: AndroidApplication) {
    @Provides @Singleton fun provideApplicationContext(): Context = application
    @Provides @Singleton fun provideExecutionScheduler(threadScheduler: ThreadScheduler): ExecutionScheduler = threadScheduler
}
