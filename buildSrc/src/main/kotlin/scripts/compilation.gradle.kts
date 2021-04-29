package scripts

plugins {
    id("com.android.application") apply false
    id("kotlin-android") apply false
}

android {
    packagingOptions {
        exclude("LICENSE.txt")
        exclude("META-INF/DEPENDENCIES")
        exclude("META-INF/ASL2.0")
        exclude("META-INF/NOTICE")
        exclude("META-INF/NOTICE")
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    testOptions {
        animationsDisabled = true
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }
}
