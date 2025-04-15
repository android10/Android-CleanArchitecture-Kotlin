import org.jetbrains.kotlin.gradle.dsl.JvmTarget


class AppConfig {
    val id = "com.fernandocejas.sample"
    val versionCode = 2
    val versionName = "2.0"

    val compileSdk = libs.versions.compileSdk.get().toInt()
    val minSdk = libs.versions.minSdk.get().toInt()
    val targetSdk = libs.versions.targetSdk.get().toInt()

    val jvmTarget = JvmTarget.JVM_17
    val jvmToolChain = 17
    val javaVersion = JavaVersion.VERSION_17
    val testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
}

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose.compiler)
    alias(libs.plugins.kotlin.ksp)
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

    buildFeatures {
        // Enables Compose functionality in Android Studio.
        // @see: https://developer.android.com/develop/ui/compose/tooling#bom
        // @see: https://developer.android.com/develop/ui/compose/bom
        compose = true
        buildConfig = true
    }

    buildTypes {
        debug {
            isMinifyEnabled = false
        }
        release {
            isMinifyEnabled = true
        }
    }
}

kotlin {
    jvmToolchain(appConfig.jvmToolChain)
    compilerOptions {
        jvmTarget.set(appConfig.jvmTarget)
    }
}

composeCompiler {
    enableStrongSkippingMode = true
}

dependencies {
    // Kotlin
    implementation(libs.kotlin.stdlib)
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.coroutines.android)

    // Android
    implementation(libs.android.appcompat)

    // Koin
    implementation(libs.koin.android)
    implementation(libs.koin.androidx.compose.navigation)

    // Logging
    implementation(libs.kermit)
    implementation(libs.ktor.client.logging)

    // Networking & Image loading
    implementation(libs.ktor.client.okhttp)
    implementation(libs.coil.compose)
    implementation(libs.coil.network.okhttp)
    implementation(libs.ktor.client.content.negotiation)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.ktor.serialization.kotlinx.json)
    implementation(libs.converter.gson)

    // Compose
    // @see: https://developer.android.google.cn/develop/ui/compose/setup?hl=en#kotlin_1
    val composeBom = platform("androidx.compose:compose-bom:2025.02.00")
    implementation(composeBom)

    // Specify Compose library dependencies without a version definition
    implementation(libs.androidx.foundation)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.material.icons.core)
    // Integration with activities
    implementation(libs.androidx.activity.compose)
    // Integration with ViewModels
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    // Integration with LiveData
    implementation(libs.androidx.runtime.livedata)
    implementation(libs.androidx.navigation.compose)

    // Android Studio Preview support
    implementation(libs.androidx.ui.tooling.preview)
    debugImplementation(libs.androidx.ui.tooling)

    // Unit/Integration tests dependencies
    testImplementation(composeBom)
    testImplementation(libs.kotest.runner.junit5)
    testImplementation(libs.kotest.assertions.core)
    testImplementation(libs.kotest.property)
    testImplementation(libs.junit)
    testImplementation(libs.mockk)
    testImplementation(libs.robolectric)

    // UI tests dependencies
    androidTestImplementation(composeBom)
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.test.manifest)
    androidTestImplementation(libs.androidx.runner)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.rules)
    androidTestImplementation(libs.androidx.espresso.intents)

    // Development/Tooling dependencies
    debugImplementation(libs.leakcanary.android)
}
