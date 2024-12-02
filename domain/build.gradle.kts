plugins {
  id("java-library")
  alias(libs.plugins.kotlin.jvm)
  `java-test-fixtures`
}

java {
  sourceCompatibility = JavaVersion.VERSION_11
  targetCompatibility = JavaVersion.VERSION_11
}

tasks.test {
  useJUnitPlatform()
}

kotlin {
  compilerOptions {
    jvmTarget = org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_11
  }
}

dependencies {
  implementation(libs.kotlinx.coroutines)
  implementation(libs.kotlinx.collections.immutable)

  testImplementation(libs.kotest.runner.junit5)
  testImplementation(libs.kotest.assersions.core)
  testImplementation(libs.kotest.property)

  testImplementation(libs.mockk)
}