//import com.google.wireless.android.sdk.stats.GradleBuildVariant.KotlinOptions
//import org.jetbrains.kotlin.config.JvmTarget

//plugins {
//  // Application Specific Plugins
//  id(BuildPlugins.androidApplication)
//  id(BuildPlugins.kotlinAndroid)
//  id(BuildPlugins.kotlinKapt)
//  id(BuildPlugins.kotlinAndroidExtensions)
//  id(BuildPlugins.androidHilt)
//
//  // Internal Script plugins
////  id(ScriptPlugins.variants)
////  id(ScriptPlugins.quality)
////  id(ScriptPlugins.compilation)
//}
//
//android {
//  compileSdkVersion(AndroidSdk.compile)
//
//  defaultConfig {
//    minSdkVersion(AndroidSdk.min)
//    targetSdkVersion(AndroidSdk.target)
//
//    applicationId = AndroidClient.appId
//    versionCode = AndroidClient.versionCode
//    versionName = AndroidClient.versionName
//    testInstrumentationRunner = AndroidClient.testRunner
//  }
//
//  sourceSets {
//    map { it.java.srcDir("src/${it.name}/kotlin") }
//
//    //TODO: Remove this when migrating the DI framework
//    getByName("main") { java.srcDir("$buildDir/generated/source/kapt/main") }
//  }
//}



plugins {
  id("com.android.application")
  id("org.jetbrains.kotlin.android")
  id("kotlin-android")
  id("kotlin-kapt")
  id("com.google.dagger.hilt.android")

//  id("kotlin-android-extensions")
//  kotlin("android.extensions")
//  id("dagger.hilt.android.plugin")
//  id("com.google.devtools.ksp")
}

//repositories {
//  google()
//  mavenCentral()
//}

android {
  namespace = "com.fernandocejas.sample"

  compileSdk = 33
  defaultConfig {
    applicationId = "com.fernandocejas.sample"
    minSdk = 29
    targetSdk = 33
    versionCode = 1
    versionName = "1.0"

    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
  }

  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
  }

  kotlinOptions {
    jvmTarget = "17"
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

//dependencies {
//  //Compile time dependencies
//  ksp(Libraries.lifecycleCompiler)
//  ksp(Libraries.hiltCompiler)
//
//  // Application dependencies
//  implementation(Libraries.kotlinStdLib)
//  implementation(Libraries.kotlinCoroutines)
//  implementation(Libraries.kotlinCoroutinesAndroid)
//  implementation(Libraries.appCompat)
//  implementation(Libraries.ktxCore)
//  implementation(Libraries.constraintLayout)
//  implementation(Libraries.viewModel)
//  implementation(Libraries.liveData)
//  implementation(Libraries.lifecycleExtensions)
//  implementation(Libraries.cardView)
//  implementation(Libraries.recyclerView)
//  implementation(Libraries.material)
//  implementation(Libraries.androidAnnotations)
//  implementation(Libraries.glide)
//  implementation(Libraries.hilt)
//  implementation(Libraries.retrofit)
//  implementation(Libraries.okHttpLoggingInterceptor)
//
//  //TODO: change this
//  implementation("androidx.fragment:fragment-ktx:1.2.5")
//
//  // Unit/Android tests dependencies
//  testImplementation(TestLibraries.junit4)
//  testImplementation(TestLibraries.mockk)
//  testImplementation(TestLibraries.kluent)
//  testImplementation(TestLibraries.robolectric)
//
//  // Acceptance tests dependencies
//  androidTestImplementation(TestLibraries.testRunner)
//  androidTestImplementation(TestLibraries.espressoCore)
//  androidTestImplementation(TestLibraries.testExtJunit)
//  androidTestImplementation(TestLibraries.testRules)
//  androidTestImplementation(TestLibraries.espressoIntents)
//  androidTestImplementation(TestLibraries.hiltTesting)
//
//  // Development dependencies
//  debugImplementation(DevLibraries.leakCanary)
//}

dependencies {
  //Compile time dependencies
//  ksp("androidx.lifecycle:lifecycle-compiler:2.6.1")
//  ksp("com.google.dagger:hilt-android-compiler:2.46.1")
  kapt("androidx.lifecycle:lifecycle-compiler:2.6.1")
//  kapt("com.google.dagger:hilt-android-compiler:2.46.1")

  implementation("com.google.dagger:hilt-android:2.44")
  kapt("com.google.dagger:hilt-android-compiler:2.44")


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
//  implementation("com.google.dagger:hilt-android:2.46.1")
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
