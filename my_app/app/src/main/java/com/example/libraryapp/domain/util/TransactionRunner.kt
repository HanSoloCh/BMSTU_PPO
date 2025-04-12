package com.example.libraryapp.domain.util

interface TransactionRunner {
    suspend fun <T> runInTransaction(block: suspend () -> T): T
}