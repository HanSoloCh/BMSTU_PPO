package com.example.libraryapp.data.mapping

import com.example.libraryapp.data.local.entity.ApuEntity
import com.example.libraryapp.domain.model.ApuModel
import kotlin.uuid.ExperimentalUuidApi

@OptIn(ExperimentalUuidApi::class)
object ApuMapper {
    fun toDomain(apuEntity: ApuEntity): ApuModel {
        return ApuModel(
            id = apuEntity.id,
            term = apuEntity.term,
            bbkId = apuEntity.bbkId
        )
    }

    fun toData(apuModel: ApuModel): ApuEntity {
        return ApuEntity(
            id = apuModel.id,
            term = apuModel.term,
            bbkId = apuModel.bbkId
        )
    }
}