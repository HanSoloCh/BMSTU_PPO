package com.example.libraryapp.domain.exception

import java.util.UUID

class ModelNotFoundException(modelName: String, modelId: UUID) :
    RuntimeException("$modelName with id $modelId not found")