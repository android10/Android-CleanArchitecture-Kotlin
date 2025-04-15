package com.fernandocejas.sample

import com.fernandocejas.sample.core.navigation.navigationFeature
import com.fernandocejas.sample.core.network.networkFeature
import com.fernandocejas.sample.features.auth.authFeature
import com.fernandocejas.sample.features.login.loginFeature
import com.fernandocejas.sample.features.movies.di.moviesFeature

fun allFeatures() = listOf(
    networkFeature(),
    authFeature(),
    loginFeature(),
    moviesFeature(),
    navigationFeature(),
)
