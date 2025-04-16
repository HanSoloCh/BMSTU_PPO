//package com.example.libraryapp.mock
//
//import com.example.libraryapp.domain.model.BbkModel
//import com.example.libraryapp.domain.repository.BbkRepository
//import kotlin.uuid.ExperimentalUuidApi
//import kotlin.uuid.Uuid
//
//@OptIn(ExperimentalUuidApi::class)
//class MockBbkRepository : BbkRepository {
//
//    val bbks = mutableListOf<BbkModel>()
//
//    override suspend fun readById(bbkId: Uuid): BbkModel? {
//        return bbks.find { it.id == bbkId }
//    }
//
//    override suspend fun create(bbkModel: BbkModel) {
//        bbks.add(bbkModel)
//    }
//
//    override suspend fun update(bbkModel: BbkModel) {
//        val index = bbks.indexOfFirst { it.id == bbkModel.id }
//        if (index != -1) {
//            bbks[index] = bbkModel
//        }
//    }
//
//    override suspend fun deleteById(bbkId: Uuid) {
//        bbks.removeAll { it.id == bbkId }
//    }
//}
