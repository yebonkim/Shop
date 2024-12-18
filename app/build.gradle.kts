import com.android.build.api.dsl.Packaging

plugins {
  alias(libs.plugins.android.application)
  alias(libs.plugins.kotlin.android)
  alias(libs.plugins.kotlin.compose)
  alias(libs.plugins.ksp)
  alias(libs.plugins.hilt)
  alias(libs.plugins.kotlin.serialization)
  alias(libs.plugins.kotlin.parcelize)
}

android {
  namespace = "com.example.shop"
  compileSdk = 34

  defaultConfig {
    applicationId = "com.example.shop"
    minSdk = 24
    targetSdk = 34
    versionCode = 1
    versionName = "1.0"

    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
  buildFeatures {
    compose = true
  }
  packaging {
    resources.excludes.addAll(
      listOf(
        "META-INF/LICENSE.md",
        "META-INF/LICENSE-notice.md",
      )
    )
  }
}

dependencies {

  implementation(libs.androidx.core.ktx)
  implementation(libs.androidx.lifecycle.runtime.ktx)
  implementation(libs.androidx.lifecycle.runtime.compose)
  implementation(libs.androidx.activity.compose)
  implementation(libs.androidx.navigation.compose)

  implementation(platform(libs.androidx.compose.bom))
  implementation(libs.androidx.ui)
  implementation(libs.androidx.ui.graphics)
  implementation(libs.androidx.ui.tooling.preview)
  implementation(libs.androidx.material3)

  implementation(project(":domain"))
  implementation(project(":data"))
  implementation(project(":design-system"))

  ksp(libs.hilt.compiler)
  implementation(libs.hilt.android)

  implementation(libs.kotlinx.coroutines)
  implementation(libs.kotlinx.collections.immutable)

  implementation(libs.mavericks)
  implementation(libs.mavericks.hilt)
  implementation(libs.mavericks.compose)
  implementation(libs.mavericks.testing)

  implementation(libs.kotlinx.serialization.json)

  testImplementation(testFixtures(project(":domain")))

  testImplementation(libs.junit)
  testImplementation(libs.mockk)
  testImplementation(libs.kotlinx.coroutines.test)
}