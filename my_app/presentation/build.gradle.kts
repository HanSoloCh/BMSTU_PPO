plugins {
    kotlin("jvm")
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
    implementation(libs.ktor.server.cors) // Для CORS

    // Для Koin
    implementation(libs.koin.ktor)
    implementation(libs.koin.logger.slf4j) // Logger для Koin
    implementation(libs.logback.classic)

    // Exposed
    implementation(libs.exposed.core)
}