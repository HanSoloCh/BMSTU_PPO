package com.example.libraryapp.data.mapping

import com.example.libraryapp.data.local.entity.UserEntity
import com.example.libraryapp.domain.model.UserModel
import kotlin.uuid.ExperimentalUuidApi

@OptIn(ExperimentalUuidApi::class)
object UserMapper {
    fun toDomain(userEntity: UserEntity): UserModel {
        return UserModel(
            id = userEntity.id,
            name = userEntity.name,
            surname = userEntity.name,
            secondName = userEntity.secondName,
            password = userEntity.password,
            email = userEntity.email,
            phoneNumber = userEntity.phoneNumber,
            role = userEntity.role
        )
    }

    fun toData(userModel: UserModel): UserEntity {
        return UserEntity(
            id = userModel.id,
            name = userModel.name,
            surname = userModel.name,
            secondName = userModel.secondName,
            password = userModel.password,
            email = userModel.email,
            phoneNumber = userModel.phoneNumber,
            role = userModel.role
        )
    }
}