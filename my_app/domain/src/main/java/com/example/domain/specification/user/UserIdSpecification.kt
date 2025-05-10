package com.example.libraryapp.domain.specification.user

import com.example.libraryapp.domain.model.UserModel
import com.example.libraryapp.domain.specification.Specification
import java.util.UUID

class UserIdSpecification(val id: UUID) : Specification<UserModel> {
    override fun specified(candidate: UserModel): Boolean = candidate.id == id
}