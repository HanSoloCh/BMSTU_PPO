package com.example.ui.model.exception

class InvalidDateException(string: String) : BaseDomainException("Invalid date: $string")