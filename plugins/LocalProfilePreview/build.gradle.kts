plugins {
    id("com.android.library")
    id("com.aliucord.gradle")
    kotlin("android")
}
android {
    namespace = "com.github.yournamehere"
    compileSdk = 34
    defaultConfig { minSdk = 21 }
}

