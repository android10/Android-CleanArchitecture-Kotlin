@file:Suppress("UnstableApiUsage")

import org.jetbrains.kotlin.config.JvmTarget


class AppConfig {
    val id = "com.fernandocejas.sample"
    val versionCode = 1
    val versionName = "1.0"

    val compileSdk = libs.versions.compileSdk.get().toInt()
    val minSdk = libs.versions.minSdk.get().toInt()
    val targetSdk = libs.versions.targetSdk.get().toInt()

    val javaVersion = JavaVersion.VERSION_17
    val testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
}

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
//    alias(libs.plugins.kotlin.compose.compiler)
    alias(libs.plugins.kotlin.kasp)
    alias(libs.plugins.kotlin.parcelize)
}

val appConfig = AppConfig()

android {

    namespace = appConfig.id
    compileSdk = appConfig.compileSdk

    defaultConfig {
        applicationId = appConfig.id

        minSdk = appConfig.minSdk
        targetSdk = appConfig.targetSdk
        versionCode = appConfig.versionCode
        versionName = appConfig.versionName

        testInstrumentationRunner = appConfig.testInstrumentationRunner
    }

    compileOptions {
        sourceCompatibility = appConfig.javaVersion
        targetCompatibility = appConfig.javaVersion
    }

//    composeCompiler {
//        enableStrongSkippingMode = true
//    }

    buildTypes {
        getByName("debug") {
            isMinifyEnabled = false
        }
        getByName("release") {
            isMinifyEnabled = false
        }
    }
}

// https://kotlinlang.org/docs/gradle-configure-project.html#gradle-java-toolchains-support
kotlin {
//    jvmToolchain(appConfig.javaVersion.toString().toInt())
    compilerOptions {
//        jvmTarget.set(JvmTarget.JVM_17)
    }
}

dependencies {
    //Compile time dependencies
//    implementation(libs.kotlin.compose.compiler.plugin.embeddable)
//    implementation(libs.kotlin.compose.compiler.plugin)

    // Application dependencies
    implementation(libs.kotlin.stdlib.jdk8)
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.koin.android)
    implementation(libs.android.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.annotation)
//    implementation(libs.glide)
    implementation(libs.converter.gson)

    // Unit/Integration tests dependencies
    testImplementation(libs.kotest.runner.junit5)
    testImplementation(libs.kotest.assertions.core)
    testImplementation(libs.kotest.property)
    testImplementation(libs.junit)
    testImplementation(libs.mockk)
    testImplementation(libs.robolectric)

    // UI tests dependencies
    androidTestImplementation(libs.androidx.runner)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.rules)
    androidTestImplementation(libs.androidx.espresso.intents)

    // Development/Tooling dependencies
    debugImplementation(libs.leakcanary.android)
}
