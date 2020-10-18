package scripts

plugins { id("com.android.application") apply false }

private object BuildTypes {
    const val DEBUG = "debug"
    const val RELEASE = "release"
}

private object ProductFlavors {
    const val DEV = "dev"
    const val INTERNAL = "internal"
    const val PUBLIC = "public"
}

private object FlavorDimensions {
    const val DEFAULT = "default"
}

object Default {
    const val BUILD_TYPE = BuildTypes.DEBUG
    const val BUILD_FLAVOR = ProductFlavors.DEV

    val BUILD_VARIANT = "${BUILD_FLAVOR.capitalize()}${BUILD_TYPE.capitalize()}"
}

android {
    buildTypes {
        getByName(BuildTypes.DEBUG) {
            isMinifyEnabled = false
            applicationIdSuffix = ".${BuildTypes.DEBUG}"
            isDebuggable = true
        }
        getByName(BuildTypes.RELEASE) {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    flavorDimensions(FlavorDimensions.DEFAULT)
    productFlavors {
        create(ProductFlavors.DEV) {
            dimension = FlavorDimensions.DEFAULT
            applicationIdSuffix = ".${ProductFlavors.DEV}"
            versionNameSuffix = "-${ProductFlavors.DEV}"
        }
        create(ProductFlavors.INTERNAL) {
            dimension = FlavorDimensions.DEFAULT
            applicationIdSuffix = ".${ProductFlavors.INTERNAL}"
            versionNameSuffix = "-${ProductFlavors.INTERNAL}"
        }
        create(ProductFlavors.PUBLIC) {
            dimension = FlavorDimensions.DEFAULT
        }
    }
}