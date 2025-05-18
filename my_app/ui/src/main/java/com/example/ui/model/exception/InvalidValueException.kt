package com.example.ui.model.exception

class InvalidValueException(name: String, value: String): BaseDomainException("Invalid Value for $name: $value")