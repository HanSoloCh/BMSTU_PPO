import org.jetbrains.exposed.sql.Database

object DatabaseConfig {
    fun connectToDatabase() {
        Database.connect(
            url = "jdbc:postgresql://localhost:5432/librarydb",
            driver = "org.postgresql.Driver",
            user = "libraryuser",
            password = "secret123"
        )
    }

//    private fun loadConfig(): Map<String, String> {
//        return mapOf(
//            "url" to "jdbc:postgresql://localhost:5432/librarydb",
//            "user" to "libraryuser",
//            "password" to "secret123"
//        )
//    }
}
