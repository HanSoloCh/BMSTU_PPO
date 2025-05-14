plugins {
    kotlin("jvm")
    kotlin("plugin.serialization") version "1.9.0"  // Serialization plugin
}

dependencies {
    implementation(kotlin("stdlib"))

    implementation(libs.kotlinx.coroutines.core)

    // Сериализация
    implementation(libs.ktor.serialization.kotlinx.json)
}