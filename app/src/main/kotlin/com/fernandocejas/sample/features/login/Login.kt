package com.fernandocejas.sample.features.login

import com.fernandocejas.sample.core.Feature
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

fun loginFeature() = object : Feature {

    override fun name() = "login"

    override fun diModule() = module {
        singleOf(::Authenticator)
    }
}
