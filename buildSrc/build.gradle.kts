object Dependencies {
    object Versions {
        /** Must update Dependencies.kt as applicable, when changing this */
        const val androidGradlePlugin = "7.0.2"

        /** Must update Dependencies.kt as applicable, when changing this */
        const val kotlinGradlePlugin = "1.5.31"

        const val detektGradlePlugin = "1.14.1"
    }

    const val androidGradlePlugin = "com.android.tools.build:gradle:${Versions.androidGradlePlugin}"
    const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlinGradlePlugin}"
    const val detektGradlePlugin = "io.gitlab.arturbosch.detekt:detekt-gradle-plugin:${Versions.detektGradlePlugin}"
}

plugins {
    `kotlin-dsl`
    `kotlin-dsl-precompiled-script-plugins`
}

repositories {
    google()
    mavenCentral()
}

dependencies {
    implementation(Dependencies.androidGradlePlugin)
    implementation(Dependencies.kotlinGradlePlugin)
    implementation(Dependencies.detektGradlePlugin)
}
