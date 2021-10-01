plugins {
  id(ScriptPlugins.infrastructure)
  id(BuildPlugins.versionsPlugin) version(BuildPlugins.Versions.versions)
}

buildscript {
  repositories {
    google()
    mavenCentral()
  }

  dependencies {
    classpath (BuildPlugins.androidGradlePlugin)
    classpath (BuildPlugins.kotlinGradlePlugin)
    classpath (BuildPlugins.hiltGradlePlugin)
  }
}

allprojects {
  repositories {
    google()
    mavenCentral()

    // Required by Detekt
    maven { url = uri("https://maven.pkg.jetbrains.space/public/p/kotlinx-html/maven") }
  }
}
