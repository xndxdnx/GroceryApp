plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)

    // DaggerHilt
    alias(libs.plugins.hilt)
    //KSP
    alias(libs.plugins.ksp)

    // Serialization
    alias(libs.plugins.kotlin.serialization)
    // Firebase Google service
    alias(libs.plugins.google.gms.google.services)
}

android {
    namespace = "com.example.groceryapp"
    compileSdk {
        version = release(36)
    }

    defaultConfig {
        applicationId = "com.example.groceryapp"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
    // KSP для Room & Room
    // Добавляем библиотеки Room через Version Catalog
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)
    // Передаем компилятор Room в обработчик KSP
    ksp(libs.androidx.room.compiler)


    // Dagger Hilt
    implementation(libs.hilt.android)
    implementation(libs.hilt.navigation.compose)
    implementation(libs.firebase.database)
    ksp(libs.hilt.compiler)

    // Serialization
        // Навигация
    implementation(libs.androidx.navigation.compose) // Берется из [libraries] -> androidx-navigation-compose
        // Библиотека для поддержки @Serializable (без неё код не скомпилируется!)
    implementation(libs.kotlinx.serialization.json)

        // BoM и другие Firebase
    // Добавляем библиотеки BoM через Version Catalog
    implementation(platform(libs.firebase.bom)) // <-- Сам Bom
    implementation(libs.firebase.firestore)     // <-- другие FireBase модули просто указываем здесь и всё остальное
    implementation(libs.firebase.database)     // <-- другие FireBase модули просто указываем здесь и всё остальное
    implementation(libs.firebase.auth)          //  BoM скачает и подключит самостоятельно



    implementation(libs.androidx.material3)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
}