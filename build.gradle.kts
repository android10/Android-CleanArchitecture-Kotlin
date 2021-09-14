plugins {
  id(ScriptPlugins.infrastructure)
}

buildscript {
  repositories {
    google()
    jcenter()
  }

  dependencies {
    classpath (BuildPlugins.androidGradlePlugin)
    classpath (BuildPlugins.kotlinGradlePlugin)
    classpath (BuildPlugins.hiltGradlePlugin)
      classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.30")
  }
}

allprojects {
  repositories {
    google()
    jcenter()
  }
}
