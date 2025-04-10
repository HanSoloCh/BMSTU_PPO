package com.example.libraryapp.data.mapping

import com.example.libraryapp.data.local.entity.IssuanceEntity
import com.example.libraryapp.domain.model.IssuanceModel
import kotlin.uuid.ExperimentalUuidApi

@OptIn(ExperimentalUuidApi::class)
object IssuanceMapper {
    fun toDomain(issuanceEntity: IssuanceEntity): IssuanceModel {
        return IssuanceModel(
            id = issuanceEntity.id,
            bookId = issuanceEntity.bookId,
            userId = issuanceEntity.userId,
            issuanceDate = issuanceEntity.issuanceDate,
            returnDate = issuanceEntity.returnDate,
            extensionsCount = issuanceEntity.extensionsCount
        )
    }

    fun toData(issuanceModel: IssuanceModel): IssuanceEntity {
        return IssuanceEntity(
            id = issuanceModel.id,
            bookId = issuanceModel.bookId,
            userId = issuanceModel.userId,
            issuanceDate = issuanceModel.issuanceDate,
            returnDate = issuanceModel.returnDate,
            extensionsCount = issuanceModel.extensionsCount
        )
    }
}