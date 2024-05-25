import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpackConfig

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    kotlin("plugin.serialization") version "2.0.0-RC3"

}

kotlin {
    androidTarget {
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }



    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "Shared"
            isStatic = true
        }
    }

    sourceSets {

        commonMain.dependencies {
            implementation("io.ktor:ktor-client-core:2.0.0")
            implementation("io.ktor:ktor-client-cio:2.0.0")
            implementation("io.ktor:ktor-client-content-negotiation:2.0.0")
            implementation("io.ktor:ktor-serialization-kotlinx-json:2.0.0")
            implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.0")

//    implementation("io.ktor:ktor-client-android:2.3.2")

//
        }

        androidMain.dependencies {
            implementation("io.ktor:ktor-client-cio:2.0.0")
            implementation("io.ktor:ktor-client-okhttp:2.3.2")
//
        }

        iosMain.dependencies {
            implementation(libs.ktor.client.ios)
        }
    }
}

android {
    namespace = "org.example.project.shared"
    compileSdk = libs.versions.android.compileSdk.get().toInt()
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }
}

