import org.jetbrains.compose.ExperimentalComposeLibrary
import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.compose.compiler)
    // TODO: need to covert to aliased plugin
    kotlin("plugin.serialization") version libs.versions.kotlin //decompose step2

    id("app.cash.sqldelight") version libs.versions.sqlite.driver //sqldelight step1

}

kotlin {
    androidTarget {
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }
    
    jvm("desktop")
    
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ComposeApp"
            isStatic = true
        }
    }
    
    sourceSets {
        val desktopMain by getting
        

        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)

            implementation(compose.material3)
            implementation(compose.components.resources)
            implementation(libs.kotlinx.coroutines.core)
            implementation(libs.kotlinx.serialization.json)

            implementation(libs.ktor.client.core)
            implementation(libs.ktor.client.content.negotiation)
            implementation(libs.ktor.serialization.kotlinx.json)

            implementation(libs.mvvm.core)

            api(libs.image.loader)


            implementation(libs.com.arkivanov.decompose.decompose)
            implementation(libs.decompose.extensions.compose)
            //decompose step1

            //koin step1
            implementation(libs.koin.core)

            implementation(libs.material3.windowsizeclass.multiplatform)

            implementation(libs.androidx.ui.tooling.preview)

            // coil
            implementation(libs.coil)

            implementation(libs.androidx.lifecycle.runtime.ktx)

            // voyager
            implementation(libs.navigator)
            implementation(libs.navigator.screen.model)
            implementation(libs.navigator.transitions)
            implementation(libs.navigator.koin)
            implementation(libs.koin.core)

        }
        desktopMain.dependencies {
            implementation(compose.desktop.currentOs)
            implementation(libs.sqlite.driver)

        }
        androidMain.dependencies {
            implementation(libs.ktor.client.android)

            //koin step2
            implementation(libs.koin.android)

            implementation(libs.androidx.activity.compose)

            implementation(libs.coil)
            implementation(libs.androidx.ui.tooling.preview)

        }

        iosMain.dependencies {
            implementation(libs.ktor.client.darwin)

            api(libs.com.arkivanov.decompose.decompose)
            api(libs.essenty.lifecycle)

        }

        jsMain.dependencies {


            implementation(npm("@cashapp/sqldelight-sqljs-worker", libs.versions.sqlite.driver.get()))
            implementation(npm("sql.js", "1.8.0"))
            implementation(libs.web.worker.driver)
            implementation(devNpm("copy-webpack-plugin", "9.1.0"))
        }
    }
}

android {
    namespace = "com.example.task"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    sourceSets["main"].res.srcDirs("src/androidMain/res")
    sourceSets["main"].resources.srcDirs("src/commonMain/resources")

    defaultConfig {
        applicationId = "com.example.task"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    buildFeatures {
        compose = true
    }
    dependencies {
        debugImplementation(compose.uiTooling)
    }
}
dependencies {
    implementation(libs.androidx.annotation.jvm)
}

compose.desktop {
    application {
        mainClass = "MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "com.example.task"
            packageVersion = "1.0.0"
        }
    }
}
