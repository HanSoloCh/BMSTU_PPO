package com.example.libraryapp

import com.example.libraryapp.data.local.entity.ApuEntity
import com.example.libraryapp.data.local.entity.BbkEntity
import com.example.libraryapp.data.repository.ApuRepositoryImpl
import com.example.libraryapp.data.repository.BbkRepositoryImpl
import com.example.libraryapp.domain.model.ApuModel
import com.example.libraryapp.domain.repository.ApuRepository
import com.example.libraryapp.domain.specification.apu.ApuTermSpecification
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.test.runTest
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.insertAndGetId
import org.jetbrains.exposed.sql.transactions.transaction
import org.junit.After
import org.junit.Before
import org.junit.Test
import java.util.UUID
import org.junit.Assert.*

class ApuRepositoryImplTest {

    private lateinit var repository: ApuRepository
    private lateinit var bbkId: UUID

    @Before
    fun setup() {
        Database.connect("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;", driver = "org.h2.Driver")
        transaction {
            SchemaUtils.create(
                ApuEntity,
                BbkEntity
            )

            bbkId = BbkEntity.insertAndGetId {
                it[code] = "Test code bbk"
                it[description] = "Test description"
            }.value
        }

        repository = ApuRepositoryImpl()
    }


    @After
    fun tearDown() {
        Database.connect("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;", driver = "org.h2.Driver")
        transaction {
            SchemaUtils.drop(ApuEntity, BbkEntity)
        }
    }

    @Test
    fun createApuTest() = runTest {
        val newApu = UUID.randomUUID()
        val apu = ApuModel(
            id = newApu,
            term = "Test",
            bbkId = bbkId
        )

        val createdId = repository.create(apu)
        assertEquals(newApu, createdId)

        val found = repository.readById(createdId)
        assertNotNull(found)
        assertEquals(apu, found)
    }

    @Test
    fun deleteApuTest() = runTest {
        val newApu = UUID.randomUUID()
        val apu = ApuModel(
            id = newApu,
            term = "Test",
            bbkId = bbkId
        )

        repository.create(apu)

        BbkRepositoryImpl().deleteById(bbkId)

        val found = repository.readById(newApu)
        assertNull(found)
    }

    @Test
    fun readWithSpecTest() = runTest {
        val apu = ApuModel(
            id = UUID.randomUUID(),
            term = "Test",
            bbkId = bbkId
        )

        repository.create(apu)
        repository.create(
            ApuModel(
                id = UUID.randomUUID(),
                term = "Other APU",
                bbkId = bbkId
            )
        )


        val result = repository.query(ApuTermSpecification("Test"))
        assertEquals(apu, result.firstOrNull()?.firstOrNull())
    }

}
