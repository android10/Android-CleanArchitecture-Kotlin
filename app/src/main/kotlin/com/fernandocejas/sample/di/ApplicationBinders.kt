package com.fernandocejas.sample.di

import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(subcomponents = arrayOf(ActivitySubcomponent::class))
abstract class ApplicationBinders {
    @Binds @IntoMap @SubcomponentKey(ActivitySubcomponent.Builder::class)
    abstract fun activitySubcomponentBuilder(builder: ActivitySubcomponent.Builder): SubcomponentBuilder<*>
}
