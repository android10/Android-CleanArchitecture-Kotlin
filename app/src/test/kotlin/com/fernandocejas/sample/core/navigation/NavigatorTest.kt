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
package com.fernandocejas.sample.core.navigation

import com.fernandocejas.sample.AndroidTest
import com.fernandocejas.sample.features.auth.credentials.Authenticator
import com.fernandocejas.sample.features.login.ui.LoginActivity
import com.fernandocejas.sample.features.movies.ui.MoviesActivity
import com.fernandocejas.sample.matchers.shouldNavigateTo
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import org.junit.Before
import org.junit.Test

class NavigatorTest : AndroidTest() {

    private lateinit var navigator: Navigator

    @MockK
    private lateinit var authenticator: Authenticator

    @Before
    fun setup() {
        navigator = Navigator(authenticator)
    }

    @Test
    fun `should forward user to login screen`() {
        every { authenticator.userLoggedIn() } returns false

        navigator.showMain(context())

        verify(exactly = 1) { authenticator.userLoggedIn() }
        RouteActivity::class shouldNavigateTo LoginActivity::class
    }

    @Test
    fun `should forward user to movies screen`() {
        every { authenticator.userLoggedIn() } returns true

        navigator.showMain(context())

        verify(exactly = 1) { authenticator.userLoggedIn() }
        RouteActivity::class shouldNavigateTo MoviesActivity::class
    }
}
