package com.example.projectpam.apiservice

import com.example.projectpam.modeldata.*
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    // Autentikasi
    // Endpoint untuk login
    @POST("login.php")  // Pastikan file PHP yang digunakan sesuai (login.php)
    suspend fun login(@Body request: LoginRequest): Response<LoginResponse>

    // Endpoint untuk registrasi
    @POST("register.php")
    suspend fun register(@Body user: User): Response<LoginResponse>

    // Operasi CRUD Buku
    @GET("get_books.php")  // Menghubungkan dengan get_books.php (untuk mengambil semua buku)
    suspend fun getAllBooks(): Response<BookResponse>

    @GET("get_books.php")  // Mengambil buku berdasarkan ID
    suspend fun getBook(@Query("book_id") bookId: Int): Response<SingleBookResponse>

    @POST("add_book.php")  // Menghubungkan dengan add_book.php (untuk menambah buku)
    suspend fun addBook(@Body book: Book): Response<SingleBookResponse>

    @PUT("update_book.php")  // Menghubungkan dengan update_book.php (untuk mengupdate buku)
    suspend fun updateBook(@Body book: Book): Response<SingleBookResponse>

    @DELETE("delete_book.php")  // Menghubungkan dengan delete_book.php (untuk menghapus buku)
    suspend fun deleteBook(@Query("book_id") bookId: Int): Response<SingleBookResponse>

    @GET("search_books.php")  // Menghubungkan dengan search_books.php (untuk mencari buku)
    suspend fun searchBooks(@Query("search") query: String): Response<BookResponse>
}

