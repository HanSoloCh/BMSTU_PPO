package com.example.libraryapp.data.mapping

import com.example.libraryapp.data.entity.UserEntity
import com.example.libraryapp.domain.model.UserModel
import com.example.libraryapp.domain.util.utils.UserRole
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.statements.InsertStatement
import org.jetbrains.exposed.sql.statements.UpdateStatement
import java.util.*

object UserMapper {
    fun toDomain(row: ResultRow): UserModel {
        return UserModel(
            id = row[UserEntity.id].value,
            name = row[UserEntity.name],
            surname = row[UserEntity.surname],
            secondName = row[UserEntity.secondName],
            password = row[UserEntity.password],
            email = row[UserEntity.email],
            phoneNumber = row[UserEntity.phoneNumber],
            role = UserRole.valueOf(row[UserEntity.role])
        )
    }

    fun toInsertStatement(
        userModel: UserModel,
        statement: InsertStatement<EntityID<UUID>>
    ): InsertStatement<EntityID<UUID>> {
        return statement.also {
            it[UserEntity.id] = userModel.id
            it[UserEntity.name] = userModel.name
            it[UserEntity.surname] = userModel.surname
            it[UserEntity.secondName] = userModel.secondName
            it[UserEntity.password] = userModel.password
            it[UserEntity.email] = userModel.email
            it[UserEntity.phoneNumber] = userModel.phoneNumber
            it[UserEntity.role] = userModel.role.toString()
        }
    }

    fun toUpdateStatement(userModel: UserModel, statement: UpdateStatement): UpdateStatement {
        return statement.also {
            it[UserEntity.id] = userModel.id
            it[UserEntity.name] = userModel.name
            it[UserEntity.surname] = userModel.surname
            it[UserEntity.secondName] = userModel.secondName
            it[UserEntity.password] = userModel.password
            it[UserEntity.email] = userModel.email
            it[UserEntity.phoneNumber] = userModel.phoneNumber
            it[UserEntity.role] = userModel.role.toString()
        }
    }
}