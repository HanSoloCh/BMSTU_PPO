plugins {
    kotlin("jvm")
    kotlin("plugin.serialization") version "1.9.0"  // Serialization plugin
}

dependencies {
    implementation(kotlin("stdlib"))

    implementation(libs.kotlinx.coroutines.core)

    // Сериализация
    implementation(libs.ktor.serialization.kotlinx.json)

    // Tests
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(kotlin("test"))
    testImplementation("io.mockk:mockk:1.13.4")
    testImplementation("org.slf4j:slf4j-simple:1.7.36")
}