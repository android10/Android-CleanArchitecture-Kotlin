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
import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import io.kotest.matchers.string.shouldBeEqualIgnoringCase
import io.mockk.MockKAnnotations
import org.junit.Rule
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.Shadows
import org.robolectric.annotation.Config
import kotlin.reflect.KClass

/**
 * Base class for Android tests. Inherit from it to
 * create test cases which contain android framework
 * dependencies or components.
 *
 * @see UnitTest
 */
@RunWith(RobolectricTestRunner::class)
@Config(application = AndroidTest.ApplicationStub::class,
        manifest = Config.NONE,
        sdk = [Build.VERSION_CODES.O_MR1])
abstract class AndroidTest {

    @Rule
    @JvmField
    @Suppress("LeakingThis")
    val injectMocks = InjectMocksRule.create(this@AndroidTest)

    fun context(): Context = RuntimeEnvironment.systemContext

    internal class ApplicationStub : Application()

    object InjectMocksRule {
        fun create(testClass: Any) = TestRule { statement, _ ->
            MockKAnnotations.init(testClass, relaxUnitFun = true)
            statement
        }
    }
}
