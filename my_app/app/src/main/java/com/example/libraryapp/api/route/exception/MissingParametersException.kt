package com.example.libraryapp.api.route.exception

class MissingParametersException(param: String)
    : RuntimeException("Missing parameters for param: $param")