package com.example.projectpam.modeldata

data class Book(
    val buku_id: Int = 0,
    val judul: String,
    val penulis: String,
    val buku: String // Isi buku
)

data class BookResponse(
    val success: Boolean,
    val message: String,
    val data: List<Book>? = null
)

data class SingleBookResponse(
    val success: Boolean,
    val message: String,
    val data: Book? = null
)

