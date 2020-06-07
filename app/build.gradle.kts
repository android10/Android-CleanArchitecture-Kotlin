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
//  def applicationDependencies = rootProject.ext.mainApplication
//  def unitTestDependencies = rootProject.ext.unitTesting
//  def acceptanceTestDependencies = rootProject.ext.acceptanceTesting
//  def developmentDependencies = rootProject.ext.development
//
//  //Compile time dependencies
//  kapt applicationDependencies.archComponentsCompiler
//  kapt applicationDependencies.daggerCompiler
//  compileOnly applicationDependencies.javaxAnnotation
//  compileOnly applicationDependencies.javaxInject
//
//  //Application dependencies
//  implementation applicationDependencies.kotlin
//  implementation applicationDependencies.kotlinCoroutines
//  implementation applicationDependencies.kotlinCoroutinesAndroid
//  implementation applicationDependencies.appCompat
//  implementation applicationDependencies.constraintLayout
//  implementation applicationDependencies.archComponents
//  implementation applicationDependencies.cardView
//  implementation applicationDependencies.recyclerView
//  implementation applicationDependencies.design
//  implementation applicationDependencies.androidAnnotations
//  implementation applicationDependencies.glide
//  implementation applicationDependencies.dagger
//  implementation applicationDependencies.retrofit
//  implementation applicationDependencies.okhttpLoggingInterceptor
//
//  //Unit/Integration tests dependencies
//  testImplementation unitTestDependencies.kotlin
//  testImplementation unitTestDependencies.kotlinTest
//  testImplementation unitTestDependencies.robolectric
//  testImplementation unitTestDependencies.junit
//  testImplementation unitTestDependencies.mockito
//  testImplementation unitTestDependencies.kluent
//
//  //Acceptance tests dependencies
//  androidTestImplementation acceptanceTestDependencies.testRunner
//  androidTestImplementation acceptanceTestDependencies.testRules
//  androidTestImplementation acceptanceTestDependencies.espressoCore
//  androidTestImplementation acceptanceTestDependencies.espressoIntents
//  androidTestImplementation acceptanceTestDependencies.androidAnnotations
//
//  //Development dependencies
//  debugImplementation developmentDependencies.leakCanary
//  releaseImplementation developmentDependencies.leakCanaryNoop
//  testImplementation developmentDependencies.leakCanaryNoop
}

kotlin {
  experimental.coroutines = org.jetbrains.kotlin.gradle.dsl.Coroutines.ENABLE
}