plugins {
    alias(libs.plugins.android.application) // Pastikan plugins sudah didefinisikan di libs.versions.toml
}

android {
    namespace = "com.frendy.notes11"  // Nama package untuk aplikasi
    compileSdk = 34  // Versi SDK yang digunakan untuk kompilasi

    defaultConfig {
        applicationId = "com.frendy.notes11"  // ID aplikasi yang unik
        minSdk = 24  // Minimum SDK yang diperlukan untuk aplikasi
        targetSdk = 34  // Target SDK untuk aplikasi
        versionCode = 1  // Kode versi aplikasi
        versionName = "1.0"  // Nama versi aplikasi

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"  // Runner untuk testing
    }

    buildTypes {
        release {
            isMinifyEnabled = false  // Tidak menggunakan proguard atau minify untuk rilis
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), // Proguard default
                "proguard-rules.pro"  // File aturan proguard custom
            )
        }
    }

    buildFeatures {
        viewBinding = true  // Mengaktifkan fitur ViewBinding
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8  // Menetapkan versi Java
        targetCompatibility = JavaVersion.VERSION_1_8  // Menetapkan versi Java
    }
}

dependencies {
    // Dependencies untuk aplikasi
    implementation(libs.appcompat)  // Library untuk AppCompat
    implementation(libs.material)  // Library untuk Material Design
    implementation(libs.activity)  // Library untuk Activity
    implementation(libs.constraintlayout)  // Library untuk ConstraintLayout

    implementation("androidx.room:room-runtime:2.6.0")  // Library Room untuk database

    // Untuk testing unit
    testImplementation(libs.junit)  // Library JUnit untuk testing unit

    // Untuk testing UI
    androidTestImplementation(libs.ext.junit)  // Library JUnit untuk testing Android
    androidTestImplementation(libs.espresso.core)  // Library Espresso untuk testing UI

    // Menambahkan Room compiler untuk proses annotation
    annotationProcessor("androidx.room:room-compiler:2.6.0")  // Compiler untuk Room (jika menggunakan Java)
}
