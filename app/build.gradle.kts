import java.io.FileInputStream
import java.util.Properties

plugins {
    id(Plugins.application)
    id(Plugins.`kotlin-android`)
    id("com.google.dagger.hilt.android")
    id("kotlin-kapt")
}

android {
    namespace = "app.stocks.money"
    compileSdk = 34

    defaultConfig {
        applicationId = "app.stocks.money"
        minSdk = 27
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        debug {
            val localProperties = Properties()
            localProperties.load(FileInputStream(rootProject.file("local.properties")))
            buildConfigField("String", "VANTAGE_API_KEY", "${localProperties["VANTAGE_API_KEY"]}")
        }
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    kapt {
        correctErrorTypes = true
    }
}

dependencies {

    // Default
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)


    // Navigation
    implementNavigation()

    // Core
    implementCore()

    // Compose
    implementCompose()

    // Material
    implementMaterial()

    // implement Core
    implementCore()

    // Dagger Hilt
    implementDaggerHilt()

    // DI Module
    implementation(project(":di"))

    // Utils Module
    implementation(project(":utils"))

    // Remote Module
    implementation(project(":data:remote"))

    // Presentation
    implementation(project(":presentation"))

}