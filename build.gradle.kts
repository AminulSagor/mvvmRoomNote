// Top-level build file where you can add configuration options common to all sub-projects/modules.

plugins {
    // These plugins are applied to the subprojects, so we use 'apply false' here
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.jetbrainsKotlinAndroid) apply false
    id("com.google.devtools.ksp") version "1.9.10-1.0.13" apply false
}

buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath (libs.gradle)

        // Replace 8.3.1 with a version compatible with Gradle 8.4
    }
}



