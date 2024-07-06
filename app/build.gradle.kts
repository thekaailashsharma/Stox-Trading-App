plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("dagger.hilt.android.plugin")
    kotlin("kapt")
    id("kotlinx-serialization")
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
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)

    // Material Design Icons
    implementation("androidx.compose.material:material-icons-extended:1.5.4")

    // Navigation
    implementation("androidx.navigation:navigation-compose:2.7.5")

    //Lottie-compose
    implementation("com.airbnb.android:lottie-compose:5.2.0")

    //Ktor-Client
    implementation("io.ktor:ktor-client-core:2.3.5")
    implementation("io.ktor:ktor-client-cio:2.3.2")
    implementation("io.ktor:ktor-client-serialization:2.3.2")
    implementation("io.ktor:ktor-client-cio-jvm:2.3.2")
    implementation("io.ktor:ktor-client-content-negotiation:2.3.2")
    implementation("io.ktor:ktor-serialization-gson:2.3.2")
    implementation("io.ktor:ktor-serialization-kotlinx-json:2.3.2")
    implementation("com.google.code.gson:gson:2.8.9")
    implementation("io.ktor:ktor-client-logging:2.3.2")
    implementation("ch.qos.logback:logback-classic:1.2.11")
    implementation("io.ktor:ktor-client-android:1.6.4")
    implementation("io.ktor:ktor-client-serialization:2.3.2")
    implementation("io.ktor:ktor-client-logging-jvm:2.3.2")
    implementation("androidx.datastore:datastore-preferences:1.0.0")
//    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.4.1")
    implementation("io.ktor:ktor-client-gson:2.3.2")

    //Dagger Hilt
    implementation("com.google.dagger:hilt-android:2.48")
    implementation(platform("androidx.compose:compose-bom:2023.03.00"))
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    kapt("com.google.dagger:hilt-compiler:2.48")
    implementation("androidx.hilt:hilt-navigation-compose:1.1.0")

    // Room
    implementation("androidx.room:room-runtime:2.6.1")
    kapt("androidx.room:room-compiler:2.6.1")
    implementation("androidx.room:room-ktx:2.6.1")

    // Coil
    implementation("io.coil-kt:coil-compose:2.2.2")
    

    // Material Icons Extended
    implementation("androidx.compose.material:material-icons-extended:1.5.0")


    // DataStore
    implementation("androidx.datastore:datastore-core:1.0.0")

    // Reorderable Lists
    implementation("org.burnoutcrew.composereorderable:reorderable:0.9.6")


    // Swipe
    implementation("me.saket.swipe:swipe:1.2.0")

    // Dialogs
    implementation("io.github.vanpra.compose-material-dialogs:datetime:0.9.0")
    coreLibraryDesugaring("com.android.tools:desugar_jdk_libs:2.0.4")

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}