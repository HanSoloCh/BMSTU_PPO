package com.example.libraryapp.domain.exception

import java.util.UUID

class BookNoAvailableCopiesException(bookId: UUID) :
    RuntimeException("book with id $bookId is run out")