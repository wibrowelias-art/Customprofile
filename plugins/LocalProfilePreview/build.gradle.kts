plugins {
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.android.library)
    alias(libs.plugins.aliucord.plugin)
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
}

kotlin {
    compilerOptions {
        jvmTarget = org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_17
    }
}

dependencies {
    compileOnly(libs.discord)
    compileOnly(libs.aliucord)
    compileOnly(libs.kotlin.stdlib)
}

aliucord {
    author("wibrowelias-art", 0L)
    github("https://github.com/wibrowelias-art/Customprofile")
    deploy.set(true)
}
