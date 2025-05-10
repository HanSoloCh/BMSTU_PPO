package com.example.libraryapp.domain.exception

import java.util.UUID

class ModelDuplicateException(modelName: String, modelId: UUID) :
    RuntimeException("$modelName with id $modelId already exists")