package com.example.libraryapp.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.DatabaseConfiguration
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.libraryapp.data.local.dao.ApuDao
import com.example.libraryapp.data.local.dao.AuthorDao
import com.example.libraryapp.data.local.dao.BbkDao
import com.example.libraryapp.data.local.dao.BookDao
import com.example.libraryapp.data.local.dao.IssuanceDao
import com.example.libraryapp.data.local.dao.PublisherDao
import com.example.libraryapp.data.local.dao.ReservationDao
import com.example.libraryapp.data.local.dao.UserDao
import com.example.libraryapp.data.local.entity.ApuEntity
import com.example.libraryapp.data.local.entity.AuthorEntity
import com.example.libraryapp.data.local.entity.BbkEntity
import com.example.libraryapp.data.local.entity.BookAuthorCrossRef
import com.example.libraryapp.data.local.entity.BookEntity
import com.example.libraryapp.data.local.entity.IssuanceEntity
import com.example.libraryapp.data.local.entity.PublisherEntity
import com.example.libraryapp.data.local.entity.ReservationEntity
import com.example.libraryapp.data.local.entity.UserEntity
import com.example.libraryapp.data.local.entity.UserFavoriteCrossRef

@Database(
    entities = [
        ApuEntity::class,
        AuthorEntity::class,
        BbkEntity::class,
        BookAuthorCrossRef::class,
        BookEntity::class,
        IssuanceEntity::class,
        PublisherEntity::class,
        ReservationEntity::class,
        UserEntity::class,
        UserFavoriteCrossRef::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDataBase : RoomDatabase() {
    abstract fun apuDao(): ApuDao
    abstract fun authorDao(): AuthorDao
    abstract fun bbkDao(): BbkDao
    abstract fun bookDao(): BookDao
    abstract fun issuanceDao(): IssuanceDao
    abstract fun publisherDao(): PublisherDao
    abstract fun reservationDao(): ReservationDao
    abstract fun userDao(): UserDao


    companion object {
        fun getDatabase(ctx: Context): AppDataBase {
            val appContext = ctx.applicationContext
            val dbFile = appContext.getDatabasePath("book.db")

            return Room.databaseBuilder(
                context = appContext,
                klass = AppDataBase::class.java,
                name = dbFile.absolutePath
            ).build()
        }
    }
}