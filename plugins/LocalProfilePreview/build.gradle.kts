plugins {
    id("com.android.library")
    kotlin("android")
}

android {
    namespace = "com.github.yournamehere"
    compileSdk = 34

    defaultConfig {
        minSdk = 21
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {
    compileOnly(libs.discord)
    compileOnly(libs.aliucord)
    compileOnly(libs.androidx.core)
    compileOnly(libs.androidx.fragment)
}

aliucord {
    author("wibrowelias-art", 0L)
    github("https://github.com/wibrowelias-art/Customprofile")
}
