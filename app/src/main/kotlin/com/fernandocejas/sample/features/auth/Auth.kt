package com.fernandocejas.sample.features.auth

import com.fernandocejas.sample.core.Feature
import com.fernandocejas.sample.features.auth.credentials.Authenticator
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

fun authFeature() = object : Feature {

    override fun name() = "auth"

    override fun diModule() = module {
        singleOf(::Authenticator)
    }
}
