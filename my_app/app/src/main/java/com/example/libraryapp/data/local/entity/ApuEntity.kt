package com.example.libraryapp.data.local.entity

import org.jetbrains.exposed.dao.id.UUIDTable

object ApuEntity : UUIDTable("apu") {
    val term = varchar("term", 50)
    val bbkId = reference("bbk_id", BbkEntity)
}
