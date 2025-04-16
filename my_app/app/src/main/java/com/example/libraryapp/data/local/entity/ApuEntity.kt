package com.example.libraryapp.data.local.entity

import org.jetbrains.exposed.dao.id.UUIDTable
import org.jetbrains.exposed.sql.ReferenceOption

object ApuEntity : UUIDTable("apu") {
    val term = varchar("term", 100)
    val bbkId = reference("bbk_id", BbkEntity, onDelete = ReferenceOption.CASCADE)
}
