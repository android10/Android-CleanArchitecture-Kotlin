package com.fernandocejas.sample.di

import com.fernandocejas.sample.BaseActivity
import dagger.Module
import dagger.Provides


@Module
class ActivityModule(private val activity: BaseActivity) {
    @Provides @ForActivity fun provideActivity(): BaseActivity = activity
}
