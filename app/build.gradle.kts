@file:Suppress("UnstableApiUsage")

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
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-android")
    id("kotlin-kapt")
//    id("com.google.devtools.ksp")
    id("com.google.dagger.hilt.android")
}

android {
    val appConfig = AppConfig()

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

    kotlinOptions {
        jvmTarget = appConfig.javaVersion.toString()
    }

    buildFeatures {
        viewBinding = true
    }

    buildTypes {
        getByName("debug") {
            isMinifyEnabled = false
        }
        getByName("release") {
            isMinifyEnabled = false
        }
    }
}

dependencies {
    //Compile time dependencies
    kapt("androidx.lifecycle:lifecycle-compiler:2.6.1")
    kapt("com.google.dagger:hilt-android-compiler:2.46.1")

    implementation("com.google.dagger:hilt-android:2.46.1")

    // Application dependencies
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.8.22")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.2")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.2")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("androidx.core:core-ktx:1.10.1")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.1")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.1")
    implementation("androidx.lifecycle:lifecycle-extensions:2.2.0")

    // Update from here
    implementation("androidx.cardview:cardview:1.0.0")
    implementation("androidx.recyclerview:recyclerview:1.3.0")
    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.annotation:annotation:1.6.0")
    implementation("com.github.bumptech.glide:glide:4.15.1")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.11")

    //TODO: change this
    implementation("androidx.fragment:fragment-ktx:1.6.0")

    // Unit/Android tests dependencies
    testImplementation("junit:junit:4.13.2")
    testImplementation("io.mockk:mockk:1.13.5")
    testImplementation("org.amshove.kluent:kluent-android:1.73")
    testImplementation("org.robolectric:robolectric:4.10.3")

    // Acceptance tests dependencies
    androidTestImplementation("androidx.test:runner:1.5.2")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test:rules:1.5.0")
    androidTestImplementation("androidx.test.espresso:espresso-intents:3.5.1")
    androidTestImplementation("com.google.dagger:hilt-android-testing:2.46.1")

    // Development dependencies
    debugImplementation("com.squareup.leakcanary:leakcanary-android:2.12")
}
