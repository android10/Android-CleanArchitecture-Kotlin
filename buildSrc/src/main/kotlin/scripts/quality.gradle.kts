package scripts

import io.gitlab.arturbosch.detekt.Detekt
import scripts.Variants_gradle.*

plugins {
    id("com.android.application") apply false
    id("jacoco")
    id("io.gitlab.arturbosch.detekt")
}

android {
    lintOptions {
        isQuiet = true
        isAbortOnError = false
        isIgnoreWarnings = true
        disable("InvalidPackage")           //Some libraries have issues with this.
        disable("OldTargetApi")             //Lint gives this warning related to SDK Beta.
        disable("IconDensities")            //For testing purpose. This is safe to remove.
        disable("IconMissingDensityFolder") //For testing purpose. This is safe to remove.
    }
}

tasks.register("jacocoReport", JacocoReport::class) {
    group = "Quality"
    description = "Report code coverage on tests within the Android codebase."
    dependsOn("test${Default.BUILD_VARIANT}UnitTest")

    val buildVariantClassPath = "${Default.BUILD_FLAVOR}${Default.BUILD_TYPE.capitalize()}"
    val outputDir = "${project.buildDir}/testCoverage/html"

    reports {
        xml.isEnabled = true
        html.isEnabled = true
        html.destination = file(outputDir)
    }

    classDirectories.setFrom(fileTree(project.buildDir) {
        include(
                "**/classes/**/main/**",
                "**/intermediates/classes/$buildVariantClassPath/**",
                "**/intermediates/javac/$buildVariantClassPath/*/classes/**",
                "**/tmp/kotlin-classes/$buildVariantClassPath/**")
        exclude(
                "**/R.class",
                "**/R\$*.class",
                "**/BuildConfig.*",
                "**/Manifest*.*",
                "**/Manifest$*.class",
                "**/*Test*.*",
                "**/Injector.*",
                "android/**/*.*",
                "**/*\$Lambda$*.*",
                "**/*\$inlined$*.*",
                "**/di/*.*",
                "**/*Database.*",
                "**/*Response.*",
                "**/*Application.*",
                "**/*Entity.*")
        }
    )
    sourceDirectories.setFrom(fileTree(project.projectDir) {
        include("src/main/java/**", "src/main/kotlin/**") })
    executionData.setFrom(fileTree(project.buildDir) {
        include("**/*.exec", "**/*.ec") })

    doLast {
        println("Code Coverage Report: $outputDir/index.html")
    }
}

tasks.register("runTestCoverage") {
    group = "Quality"
    description = "Report code coverage on tests within the Android codebase."
    dependsOn("jacocoReport")
}

val detektAll by tasks.registering(Detekt::class) {
    group = "Quality"
    description = "Runs a detekt code analysis ruleset on the Android codebase."
    parallel = true
    buildUponDefaultConfig = true

    setSource(files(rootProject.projectDir))
    config.setFrom(project.rootDir.resolve("config/detekt/detekt.yml"))

    include("**/*.kt")
    exclude("**/*.kts", "*/build/*", "/buildSrc")

    reports {
        xml.enabled = true
        html.enabled = true
        txt.enabled = false
    }
}

tasks.register("runStaticCodeAnalysis") {
    description = "Run static analysis on the Android codebase."
    dependsOn(detektAll)
}

