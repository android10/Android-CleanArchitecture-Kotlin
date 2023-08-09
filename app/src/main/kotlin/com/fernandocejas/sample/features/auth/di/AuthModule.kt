package com.fernandocejas.sample.features.auth.di

import com.fernandocejas.sample.core.navigation.Navigator
import com.fernandocejas.sample.core.network.NetworkHandler
import com.fernandocejas.sample.features.auth.credentials.Authenticator
import okhttp3.OkHttpClient
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val authModule = module {
    singleOf(::Authenticator)
}
