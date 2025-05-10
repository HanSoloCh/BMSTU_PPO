import com.example.data.local.entity.ApuEntity
import com.example.data.local.entity.AuthorEntity
import com.example.data.local.entity.BbkEntity
import com.example.data.local.entity.BookAuthorCrossRef
import com.example.data.local.entity.BookEntity
import com.example.data.local.entity.IssuanceEntity
import com.example.data.local.entity.PublisherEntity
import com.example.data.local.entity.ReservationEntity
import com.example.data.local.entity.UserEntity
import com.example.data.local.entity.UserFavoriteCrossRef
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

object DatabaseConfig {
    fun connectToDatabase(): Database {
        val db = Database.connect(
            url = "jdbc:postgresql://localhost:5432/librarydb",
            driver = "org.postgresql.Driver",
            user = "libraryuser",
            password = "secret123"
        )

        transaction(db) {
            SchemaUtils.createMissingTablesAndColumns(
                ApuEntity,
                AuthorEntity,
                BbkEntity,
                BookAuthorCrossRef,
                BookEntity,
                IssuanceEntity,
                PublisherEntity,
                ReservationEntity,
                UserEntity,
                UserFavoriteCrossRef
            )
        }
        return db
    }
}
