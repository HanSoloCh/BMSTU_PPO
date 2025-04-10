package com.example.libraryapp.data.mapping

import com.example.libraryapp.data.local.entity.AuthorEntity
import com.example.libraryapp.domain.model.AuthorModel
import kotlin.uuid.ExperimentalUuidApi

@OptIn(ExperimentalUuidApi::class)
object AuthorMapper {
    fun toDomain(authorEntity: AuthorEntity): AuthorModel {
        return AuthorModel(
            id = authorEntity.id,
            name = authorEntity.name
        )
    }

    fun toData(authorModel: AuthorModel): AuthorEntity {
        return AuthorEntity(
            id = authorModel.id,
            name = authorModel.name
        )
    }
}