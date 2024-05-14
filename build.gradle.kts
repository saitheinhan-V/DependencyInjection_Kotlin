// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.jetbrainsKotlinAndroid) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.kotlin.serialization) apply false
    alias(libs.plugins.google.hilt) apply false
    alias(libs.plugins.google.ksp) apply false

//    id("com.google.dagger:hilt-android-gradle-plugin") version "2.28-alpha" apply false
}