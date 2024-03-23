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
    const val applicationNameSpace = "com.tui.challenge"
    const val dataModuleNameSpace = "com.tui.challenge.data"
}

object ClassPaths {
    const val androidTools = "com.android.tools.build:gradle:7.4.2"
    const val kotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:1.8.10"
    const val hilt = "com.google.dagger:hilt-android-gradle-plugin:2.51"
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

    object AndroidLibrary {
        const val id = "com.android.library"
    }

    object Kapt {
        const val id = "kotlin-kapt"
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

    object Retrofit {
        private const val version = "2.9.0"
        const val retrofit = "com.squareup.retrofit2:retrofit:$version"
        const val gsonConverter = "com.squareup.retrofit2:converter-gson:$version"
    }

    object Hilt {
        private const val version = "2.51"
        const val hilt = "com.google.dagger:hilt-android:$version"
        const val hiltCompiler = "com.google.dagger:hilt-android-compiler:$version"
    }

    object OkHttp {
        private const val version = "4.12.0"
        const val mockWebServer = "com.squareup.okhttp3:mockwebserver:$version"
    }
}
