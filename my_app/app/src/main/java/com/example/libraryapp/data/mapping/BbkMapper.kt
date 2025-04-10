package com.example.libraryapp.data.mapping

import com.example.libraryapp.data.local.entity.BbkEntity
import com.example.libraryapp.domain.model.BbkModel
import kotlin.uuid.ExperimentalUuidApi

@OptIn(ExperimentalUuidApi::class)
object BbkMapper {
    fun toDomain(bbkEntity: BbkEntity): BbkModel {
        return BbkModel(
            id = bbkEntity.id,
            code = bbkEntity.code,
            description = bbkEntity.description
        )
    }

    fun toData(bbkModel: BbkModel): BbkEntity {
        return BbkEntity(
            id = bbkModel.id,
            code = bbkModel.code,
            description = bbkModel.description
        )
    }
}