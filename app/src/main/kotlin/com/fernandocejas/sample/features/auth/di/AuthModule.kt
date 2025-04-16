package com.fernandocejas.sample.features.auth.di

import com.fernandocejas.sample.features.auth.credentials.Authenticator
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val authModule = module {
    singleOf(::Authenticator)
}
