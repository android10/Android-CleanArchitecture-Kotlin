package com.fernandocejas.sample.core.network

import co.touchlab.kermit.Logger
import com.fernandocejas.sample.core.di.Feature
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.cache.HttpCache
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import io.ktor.client.plugins.logging.Logger as KtorLogger

fun networkFeature() = object : Feature {
    override fun name() = "network"
    override fun diModule() = networkModule
}

private val networkModule = module {
    singleOf(::NetworkHandler)
    single { json }
    single { client }
}

private val json = Json {
    ignoreUnknownKeys = true
    explicitNulls = false
}

private val client = HttpClient(OkHttp) {
    engine {
        config {
            followRedirects(true)
        }
    }
    install(HttpCache)
    install(HttpTimeout)
    install(ContentNegotiation) {
        json(json, ContentType.Text.Plain)
    }
    install(Logging) {
        logger = object : KtorLogger {
            override fun log(message: String) {
                Logger.withTag("HTTP").d { "\uD83C\uDF10 $message" }
            }
        }
        level = LogLevel.HEADERS
    }
}
