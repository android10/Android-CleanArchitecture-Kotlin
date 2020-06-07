plugins {
  //TODO: Apply ci.gradle -> rename this to infrastructure
//  id(ScriptPlugins.infrastructure)
}

buildscript {
  repositories {
    google()
    jcenter()
  }

  dependencies {
    classpath ("com.android.tools.build:gradle:4.0.0")
    classpath ("org.jetbrains.kotlin:kotlin-gradle-plugin:1.3.72")
  }
}

allprojects {
  repositories {
    google()
    jcenter()
  }
}