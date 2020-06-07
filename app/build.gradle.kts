plugins {
  id(BuildPlugins.androidApplication)
  id(BuildPlugins.kotlinAndroid)
  id(BuildPlugins.kotlinKapt)
  id(BuildPlugins.kotlinAndroidExtensions)
}

android {
  compileSdkVersion(AndroidSdk.compile)

  defaultConfig {
    applicationId = appId
    minSdkVersion(AndroidSdk.min)
    targetSdkVersion(AndroidSdk.target)
    versionCode = 1
    versionName = "1.0"
    testInstrumentationRunner = "android.support.test.runner.AndroidJUnitRunner"
  }

  sourceSets {
    getByName("main") { java.srcDir("src/main/kotlin") }
    getByName("main") { java.srcDir("$buildDir/generated/source/kapt/main") }
    getByName("test") { java.srcDir("src/test/kotlin") }
    getByName("androidTest") { java.srcDir("src/androidTest/kotlin") }
  }

  packagingOptions {
    exclude("LICENSE.txt")
    exclude("META-INF/DEPENDENCIES")
    exclude("META-INF/ASL2.0")
    exclude("META-INF/NOTICE")
    exclude("META-INF/NOTICE")
  }

  lintOptions {
    isQuiet = true
    isAbortOnError = false
    isIgnoreWarnings = true
    disable("InvalidPackage")           //Some libraries have issues with this.
    disable("OldTargetApi")             //Lint gives this warning related to SDK Beta.
    disable("IconDensities")            //For testing purpose. This is safe to remove.
    disable("IconMissingDensityFolder") //For testing purpose. This is safe to remove.
  }

  buildTypes {
    getByName("release") {
      isMinifyEnabled = false
      proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
    }
  }
}

dependencies {
  //Compile time dependencies
  kapt(Libraries.archComponentsCompiler)
  kapt(Libraries.daggerCompiler)
  compileOnly(Libraries.javaxAnnotation)
  compileOnly(Libraries.javaxInject)

  // Application dependencies
  implementation(Libraries.kotlinStdLib)
  implementation(Libraries.kotlinCoroutines)
  implementation(Libraries.kotlinCoroutinesAndroid)
  implementation(Libraries.appCompat)
  implementation(Libraries.ktxCore)
  implementation(Libraries.constraintLayout)
  implementation(Libraries.archComponents)
  implementation(Libraries.cardView)
  implementation(Libraries.recyclerView)
  implementation(Libraries.material)
  implementation(Libraries.androidAnnotations)
  implementation(Libraries.glide)
  implementation(Libraries.dagger)
  implementation(Libraries.retrofit)
  implementation(Libraries.okhttpLoggingInterceptor)

  // Unit/Android tests dependencies
  testImplementation(TestLibraries.junit4)
  testImplementation(TestLibraries.mockito)
  testImplementation(TestLibraries.kluent)
  testImplementation(TestLibraries.robolectric)

  // Acceptance tests dependencies
  androidTestImplementation(TestLibraries.testRunner)
  androidTestImplementation(TestLibraries.espressoCore)
  androidTestImplementation(TestLibraries.testExtJunit)
  androidTestImplementation(TestLibraries.testRules)
  androidTestImplementation(TestLibraries.espressoIntents)

  // Development dependencies
  debugImplementation(DevLibraries.leakCanary)
  releaseImplementation(DevLibraries.leakCanaryNoop)
  testImplementation(DevLibraries.leakCanaryNoop)
}

kotlin {
  experimental.coroutines = org.jetbrains.kotlin.gradle.dsl.Coroutines.ENABLE
}