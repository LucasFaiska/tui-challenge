import org.gradle.api.JavaVersion

object Configuration {

    val javaVersion = JavaVersion.VERSION_17
    const val jvmTarget = "17"

    const val compileSdk = 34
    const val targetSdk = 34
    const val minSdk = 24

    const val versionCode = 1
    const val versionName = "1.0"

    const val applicationId = "com.tui.challenge"
}

object Plugins {

    object AndroidApplication {
        const val id = "com.android.application"
        const val version = "8.2.2"
    }

    object KotlinAndroid {
        const val id = "org.jetbrains.kotlin.android"
        const val version = "1.9.22"
    }

    object KotlinJvm {
        const val id = "org.jetbrains.kotlin.jvm"
        const val version = "1.9.22"
    }
}

object Dependencies {

    object AndroidX {
        const val coreKtx = "androidx.core:core-ktx:1.12.0"
        const val appCompat = "androidx.appcompat:appcompat:1.6.1"
        const val material = "com.google.android.material:material:1.11.0"
    }

    object Test {
        const val junit = "junit:junit:4.13.2"
        const val junitExt = "androidx.test.ext:junit:1.1.5"
        const val espressoCore = "androidx.test.espresso:espresso-core:3.5.1"
    }
}
