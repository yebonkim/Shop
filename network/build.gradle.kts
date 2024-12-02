plugins {
  alias(libs.plugins.android.library)
  alias(libs.plugins.kotlin.android)
  alias(libs.plugins.kotlin.serialization)
  alias(libs.plugins.ksp)
  alias(libs.plugins.hilt)
}

android {
  namespace = "com.example.shop.network"
  compileSdk = 34

  defaultConfig {
    minSdk = 24

    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    consumerProguardFiles("consumer-rules.pro")
  }

  buildTypes {
    release {
      isMinifyEnabled = false
      proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
    }
  }
  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
  }
  kotlinOptions {
    jvmTarget = "11"
  }
  testOptions {
    unitTests.all {
      it.useJUnitPlatform()
    }
  }
}

dependencies {
  implementation(libs.kotlinx.serialization.json)

  implementation(libs.ktor.client)
  implementation(libs.ktor.negotiation)
  implementation(libs.ktor.json)
  implementation(libs.ktor.logging)

  ksp(libs.hilt.compiler)
  implementation(libs.hilt.android)

  testImplementation(libs.kotest.runner.junit5)
  testImplementation(libs.kotest.assersions.core)
  testImplementation(libs.kotest.property)
}