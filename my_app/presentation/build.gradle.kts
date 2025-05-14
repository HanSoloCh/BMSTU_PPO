plugins {
    kotlin("jvm")
    kotlin("plugin.serialization") version "1.9.0"  // Serialization plugin
}

dependencies {
    implementation(project(":domain"))
    implementation(project(":data"))

    implementation(libs.kotlinx.coroutines.core)

    // Для Postgres
    implementation(libs.postgresql)

    // Сериализация
    implementation(libs.ktor.serialization.kotlinx.json)

    // Ktor
    implementation(libs.ktor.server.core)
    implementation(libs.ktor.server.netty)
    implementation(libs.ktor.server.content.negotiation)
    implementation(libs.ktor.ktor.server.status.pages)

    // Для Koin
    implementation(libs.koin.ktor)
    implementation(libs.koin.logger.slf4j) // Логирование

    // Exposed
    implementation(libs.exposed.core)

    implementation("io.ktor:ktor-server-cors:2.3.7")
}