const val projectName = "Android-CleanArchitecture-Kotlin"
const val kotlinVersion = "1.3.72"

object BuildPlugins {
    object Versions {
        const val buildToolsVersion = "4.0.0"
        const val gradleVersion = "6.5"
    }

    const val androidGradlePlugin = "com.android.tools.build:gradle:${Versions.buildToolsVersion}"
    const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion"
    const val androidApplication = "com.android.application"
    const val kotlinAndroid = "kotlin-android"
    const val kotlinAndroidExtensions = "kotlin-android-extensions"
}

object AndroidSdk {
    const val min = 21
    const val compile = 29
    const val target = compile
}

object Modules {
    const val app = ":app"
}

object Libraries {
    private object Versions {
        const val jetpack = "1.1.0"
        const val constraintLayout = "1.1.3"
        const val ktx = "1.3.0"
    }

    const val kotlinStdLib     = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlinVersion"
    const val appCompat        = "androidx.appcompat:appcompat:${Versions.jetpack}"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    const val ktxCore          = "androidx.core:core-ktx:${Versions.ktx}"
}

object TestLibraries {
    private object Versions {
        const val junit4 = "4.13"
        const val mockito = "2.7.22"
        const val robolectric = "4.3.1"
        const val assertJ = "3.16.1"
        const val testRunner = "1.1.0"
        const val espresso = "3.2.0"
        const val testExtensions = "1.1.1"
        const val testRules = "1.1.0"
    }

    const val junit4         = "junit:junit:${Versions.junit4}"
    const val mockito        = "org.mockito:mockito-core:${Versions.mockito}"
    const val robolectric    = "org.robolectric:robolectric:${Versions.robolectric}"
    const val assertJ        = "org.assertj:assertj-core:${Versions.assertJ}"
    const val testRunner     = "androidx.test:runner:${Versions.testRunner}"
    const val espresso       = "androidx.test.espresso:espresso-core:${Versions.espresso}"
    const val testExtJunit   = "androidx.test.ext:junit:${Versions.testExtensions}"
    const val testRules      = "androidx.test:rules:${Versions.testRules}"
    const val mockitoAndroid = "org.mockito:mockito-android:${Versions.mockito}"
}

object ScriptPlugins {
    const val infrastructure = "scripts.infrastructure"
}