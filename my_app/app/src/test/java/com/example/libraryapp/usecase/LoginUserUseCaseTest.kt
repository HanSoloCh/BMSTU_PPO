//package com.example.libraryapp.usecase
//
//import com.example.libraryapp.domain.model.UserModel
//import com.example.libraryapp.domain.usecase.LoginUserUseCase
//import com.example.libraryapp.domain.util.utils.UserRole
//import com.example.libraryapp.mock.MockUserRepository
//import kotlinx.coroutines.test.runTest
//import org.junit.Assert.*
//import org.junit.Before
//import org.junit.Test
//import kotlin.uuid.ExperimentalUuidApi
//import kotlin.uuid.Uuid
//
//@OptIn(ExperimentalUuidApi::class)
//class LoginUserUseCaseTest {
//
//    private lateinit var useCase: LoginUserUseCase
//    private lateinit var repository: MockUserRepository
//
//    @Before
//    fun setUp() {
//        repository = MockUserRepository()
//        repository.apply {
//            users.add(
//                UserModel(
//                id = Uuid.random(),
//                email = "user@example.com",
//                password = "password123",
//                name = "MOCK",
//                surname = "MOCK",
//                secondName = "MOCK",
//                phoneNumber = "89991233223",
//                role = UserRole.READER)
//            )
//        }
//        useCase = LoginUserUseCase(repository)
//    }
//
//    @Test
//    fun `returns user when correct email and password`() = runTest {
//        val result = useCase("user@example.com", "password123")
//        assertNotNull(result)
//        assertEquals("user@example.com", result?.email)
//    }
//
//    @Test
//    fun `returns null when email is incorrect`() = runTest {
//        val result = useCase("wrong@example.com", "password123")
//        assertNull(result)
//    }
//
//    @Test
//    fun `returns null when password is incorrect`() = runTest {
//        val result = useCase("user@example.com", "wrongpass")
//        assertNull(result)
//    }
//
//    @Test
//    fun `returns null when email is blank`() = runTest {
//        val result = useCase("", "password123")
//        assertNull(result)
//    }
//
//    @Test
//    fun `returns null when password is blank`() = runTest {
//        val result = useCase("user@example.com", "")
//        assertNull(result)
//    }
//}
