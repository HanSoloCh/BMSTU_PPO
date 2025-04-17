package com.example.libraryapp.data

import com.example.libraryapp.data.local.entity.BbkEntity
import com.example.libraryapp.data.repository.ApuRepositoryImpl
import com.example.libraryapp.data.repository.BbkRepositoryImpl
import com.example.libraryapp.domain.model.ApuModel
import com.example.libraryapp.domain.specification.apu.ApuTermSpecification
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.test.runTest
import org.jetbrains.exposed.sql.insertAndGetId
import org.jetbrains.exposed.sql.transactions.transaction
import org.junit.After
import org.junit.Before
import org.junit.Test
import java.util.UUID
import org.junit.Assert.*

class ApuRepositoryImplTest : BasePostgresIntegrationTest() {

    private val repository = ApuRepositoryImpl()
    private lateinit var bbkId: UUID

    @Before
    fun setupTest() {
        setUpDatabase()
        transaction(db) {
            bbkId = BbkEntity.insertAndGetId {
                it[code] = "Test Code"
                it[description] = "Test Description"
            }.value
        }
    }

    @After
    fun endTest() {
        tearDownDatabase()
    }

    @Test
    fun createApuTest() = runTest {

        val apu = ApuModel(UUID.randomUUID(), "Test", bbkId)
        val id = repository.create(apu)
        assertEquals(apu.id, id)

        val found = repository.readById(id)
        assertNotNull(found)
        assertEquals(apu, found)
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
}
