plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.aliucord.plugin)
    alias(libs.plugins.kotlin.android)
}

version = "1.0.0"
description = "Local profile preview"

android {
    namespace = "com.github.yournamehere"
    compileSdk = 36

    defaultConfig {
        minSdk = 21
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

kotlin {
    compilerOptions {
        jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_17)
    }
}

aliucord {
    author("wibrowelias-art", 0L)
    github("https://github.com/wibrowelias-art/Customprofile")
}

dependencies {
    compileOnly(libs.discord)
    compileOnly(libs.aliucord)
    compileOnly(libs.kotlin.stdlib)
}
