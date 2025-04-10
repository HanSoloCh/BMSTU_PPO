package com.example.libraryapp.domain.model

import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid


data class ApuModel @OptIn(ExperimentalUuidApi::class) constructor(
    val id: Uuid,
    val term: String,
    val bbkId: Uuid
) {
    init {
        require(term.isNotBlank())
    }
}
