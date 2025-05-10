package com.example.libraryapp.api.route.exception

class ConversionFailureException(param: String)
    : RuntimeException("Cant convert $param to API call")