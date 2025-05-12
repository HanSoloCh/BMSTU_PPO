package com.example.domain.exception

import java.util.*

class BookNoAvailableCopiesException(bookId: UUID) :
    BaseDomainException("book with id $bookId is run out")