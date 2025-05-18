package com.example.ui.model.exception

class EmptyStringException(string: String) :
    BaseDomainException("$string must be not empty.")