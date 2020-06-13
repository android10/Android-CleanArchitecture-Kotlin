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
  }
}

allprojects {
  repositories {
    google()
    jcenter()
  }
}
