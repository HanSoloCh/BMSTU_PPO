package com.example.libraryapp.data.mapping

import android.util.Patterns
import com.example.libraryapp.data.local.entity.PublisherEntity
import com.example.libraryapp.domain.model.PublisherModel
import java.time.Year
import kotlin.uuid.ExperimentalUuidApi

@OptIn(ExperimentalUuidApi::class)
object PublisherMapper {
    fun toDomain(publisherEntity: PublisherEntity): PublisherModel {
        return PublisherModel(
            id = publisherEntity.id,
            name = publisherEntity.name,
            description = publisherEntity.description,
            foundationYear = publisherEntity.foundationYear,
            email = publisherEntity.email,
            phoneNumber = publisherEntity.phoneNumber
        )
    }

    fun toData(publisherModel: PublisherModel): PublisherEntity {
        return PublisherEntity(
            id = publisherModel.id,
            name = publisherModel.name,
            description = publisherModel.description,
            foundationYear = publisherModel.foundationYear,
            email = publisherModel.email,
            phoneNumber = publisherModel.phoneNumber
        )
    }
}