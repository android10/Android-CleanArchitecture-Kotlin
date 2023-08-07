/**
 * Copyright (C) 2020 Fernando Cejas Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.fernandocejas.sample

import android.app.Application
import com.fernandocejas.sample.core.allFeatures
import com.fernandocejas.sample.core.di.coreModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class AndroidApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        /**
         * Dependency Injection Initialization via Koin.
         *
         * @see [https://insert-koin.io/docs/setup/koin]
         * @see [https://insert-koin.io/docs/reference/koin-android/start/]
         */
        startKoin {
            androidContext(this@AndroidApplication)
            androidLogger()
            modules(allFeatures().map { it.diModule() })
        }
    }
}
