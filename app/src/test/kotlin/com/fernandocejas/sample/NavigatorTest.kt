package com.fernandocejas.sample

import com.fernandocejas.sample.features.login.Authenticator
import com.fernandocejas.sample.features.movies.LoginActivity
import com.fernandocejas.sample.features.movies.MoviesActivity
import com.fernandocejas.sample.framework.AndroidTest
import com.fernandocejas.sample.framework.shouldNavigateTo
import com.nhaarman.mockito_kotlin.whenever
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.verify


class NavigatorTest : AndroidTest() {
    lateinit var navigator: Navigator

    @Mock lateinit var authenticator: Authenticator

    @Before
    fun setup() {
        navigator = Navigator(authenticator)
    }

    @Test
    fun forwardUserToLoginScreen() {
        whenever(authenticator.userLoggedIn()).thenReturn(false)

        navigator.showMainScreen(context())

        verify(authenticator).userLoggedIn()
        RouteActivity::class shouldNavigateTo LoginActivity::class
    }

    @Test
    fun forwardUserToMoviesScreen() {
        whenever(authenticator.userLoggedIn()).thenReturn(true)

        navigator.showMainScreen(context())

        verify(authenticator).userLoggedIn()
        RouteActivity::class shouldNavigateTo MoviesActivity::class
    }
}
