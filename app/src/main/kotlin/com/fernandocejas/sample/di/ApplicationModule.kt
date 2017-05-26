package com.fernandocejas.sample.di

import android.content.Context
import com.fernandocejas.sample.AndroidApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class ApplicationModule(private val application: AndroidApplication) {
    @Provides @Singleton fun provideApplicationContext(): Context = application
}
