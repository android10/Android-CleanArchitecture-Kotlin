object Dependencies {
    const val AndroidBuildTools = "com.android.tools.build:gradle:4.1.0"
    const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:1.4.10"
    const val detektGradlePlugin = "io.gitlab.arturbosch.detekt:detekt-gradle-plugin:1.14.1"
}

plugins {
    `kotlin-dsl`
    `kotlin-dsl-precompiled-script-plugins`
}

repositories {
    jcenter()
    google()
}

dependencies {
    implementation(Dependencies.AndroidBuildTools)
    implementation(Dependencies.kotlinGradlePlugin)
    implementation(Dependencies.detektGradlePlugin)
}
