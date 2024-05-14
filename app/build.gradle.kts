plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.google.ksp)
    alias(libs.plugins.google.hilt)
//    kotlin("ksp")
//    id("com.google.dagger.hilt.android")
//    id("com.google.devtools.ksp")

}


android {
    namespace = "com.my.di"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.my.di"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
        multiDexEnabled = true

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    //Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.okhttp3:okhttp:4.12.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.12.0")

    //live data //view model
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.7.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.7.0")


    implementation(libs.google.hilt.android)
    ksp(libs.google.hilt.compiler)
    implementation(libs.kotlinx.json)

//    implementation("com.google.dagger:hilt-android:2.50")
////    kapt("com.google.dagger:hilt-android-compiler:2.28-alpha")
//    implementation("androidx.hilt:hilt-lifecycle-viewmodel:1.0.0-alpha01")
////    kapt("androidx.hilt:hilt-compiler:1.0.0-alpha01")
//    kapt("com.google.dagger:hilt-android-compiler:2.50")


    implementation(libs.kotlinx.json)
    implementation(libs.network.profiler)
    implementation(libs.network.serialization)
    implementation("androidx.activity:activity-ktx:1.1.0")
    implementation("com.github.bumptech.glide:glide:4.11.0")


}
