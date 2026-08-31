plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.aliucord)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.github.yournamehere"
    compileSdk = 36

    defaultConfig {
        minSdk = 21
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }
}

aliucord {
    author("YourName", 0L)
    github("https://github.com/wibrowelias-art/Customprofile")
}

dependencies {
    compileOnly(libs.discord)
    compileOnly(libs.aliucord)
    compileOnly(libs.kotlin.stdlib)
}
