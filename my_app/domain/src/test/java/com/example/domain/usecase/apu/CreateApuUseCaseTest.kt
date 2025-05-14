package com.example.domain.usecase.apu

import com.example.domain.model.ApuModel
import com.example.domain.model.TestApu
import com.example.domain.repository.ApuRepository
import com.example.domain.repository.BbkRepository
import com.example.domain.specification.apu.ApuIdSpecification
import com.example.domain.specification.bbk.BbkIdSpecification
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import java.util.UUID
import kotlin.test.assertEquals

class CreateApuUseCaseTest {
    private val apuRepository: ApuRepository = mockk()
    private val bbkRepository: BbkRepository = mockk()

    private val createUseCase = CreateApuUseCase(apuRepository, bbkRepository)

    private lateinit var testApu: ApuModel

    @Before
    fun setup() {
        testApu = TestApu.create(bbkId = UUID.randomUUID())
    }

    @Test
    fun `simple create apu test`() = runTest {

        coEvery { apuRepository.isContain(ApuIdSpecification(testApu.id)) } returns false
        coEvery { bbkRepository.isContain(BbkIdSpecification(testApu.id)) } returns true
        coEvery { apuRepository.create(testApu) } returns testApu.id

        val createdId = createUseCase(testApu)

        assertEquals(testApu.id, createdId)

        coVerify { apuRepository.isContain(ApuIdSpecification(testApu.id)) }
        coVerify { bbkRepository.isContain(BbkIdSpecification(testApu.id)) }
        coVerify { apuRepository.create(testApu) }
    }
}