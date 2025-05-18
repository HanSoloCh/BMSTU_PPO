package com.example.ui.model.exception

class InvalidPhoneException(phone: String) :
        BaseDomainException("Invalid phone number $phone")