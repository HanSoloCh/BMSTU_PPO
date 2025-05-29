package com.example.data.local.entity

import org.jetbrains.exposed.dao.id.IdTable
import java.util.*

object LanguageEntity : IdTable<UUID>("language") {
    override val id = uuid("id").entityId()
    val name = text("name")
}