package com.example.app.exception

class ConversionFailureException(param: String) : RuntimeException("Cant convert $param to API call")