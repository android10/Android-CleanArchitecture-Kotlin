package com.fernandocejas.sample.core.di

import com.fernandocejas.sample.core.navigation.Navigator
import com.fernandocejas.sample.core.network.NetworkHandler
import okhttp3.OkHttpClient
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val coreModule = module {
    singleOf(::retrofit)
    singleOf(::NetworkHandler)
    singleOf(::Navigator)
}

private fun retrofit(): Retrofit {
    return Retrofit.Builder()
        .baseUrl("https://raw.githubusercontent.com/android10/Sample-Data/master/Android-CleanArchitecture-Kotlin/")
        .client(OkHttpClient.Builder().build())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}
