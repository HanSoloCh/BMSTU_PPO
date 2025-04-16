//package com.example.libraryapp.mock
//
//import com.example.libraryapp.domain.model.UserModel
//import com.example.libraryapp.domain.repository.UserRepository
//import kotlin.uuid.ExperimentalUuidApi
//import kotlin.uuid.Uuid
//
//@OptIn(ExperimentalUuidApi::class)
//class MockUserRepository : UserRepository {
//    val users = mutableListOf<UserModel>()
//
//    override suspend fun create(userModel: UserModel) {
//        users.add(userModel)
//    }
//
//    override suspend fun readById(userId: Uuid): UserModel? {
//        return users.find { it.id == userId }
//    }
//
//    override suspend fun update(userModel: UserModel) {
//        val index = users.indexOfFirst { it.id == userModel.id }
//        if (index != -1) {
//            users[index] = userModel
//        }
//    }
//
//    override suspend fun deleteById(userId: Uuid) {
//        users.removeAll { it.id == userId }
//    }
//
//    override suspend fun login(email: String, password: String): UserModel? {
//        return users.find { it.email == email && it.password == password }
//    }
//}
