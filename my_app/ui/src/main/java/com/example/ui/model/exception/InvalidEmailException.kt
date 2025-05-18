package com.example.ui.model.exception

class InvalidEmailException(email: String) :
        BaseDomainException("Invalid email: $email")