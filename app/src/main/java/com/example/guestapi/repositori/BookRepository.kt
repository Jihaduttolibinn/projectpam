package com.example.guestapi.repositori

import com.example.guestapi.apiservice.RetrofitClient
import com.example.guestapi.modeldata.Book
import com.example.guestapi.modeldata.BookResponse
import com.example.guestapi.modeldata.SingleBookResponse

class BookRepository {
    
    private val apiService = RetrofitClient.apiService
    
    suspend fun getAllBooks(): Result<List<Book>> {
        return try {
            val response = apiService.getAllBooks()
            if (response.isSuccessful && response.body()?.success == true) {
                Result.success(response.body()?.data ?: emptyList())
            } else {
                Result.failure(Exception("Gagal mengambil data buku"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    suspend fun getBook(bookId: Int): Result<Book> {
        return try {
            val response = apiService.getBook(bookId)
            if (response.isSuccessful && response.body()?.success == true) {
                Result.success(response.body()?.data!!)
            } else {
                Result.failure(Exception("Buku tidak ditemukan"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    suspend fun addBook(book: Book): Result<Book> {
        return try {
            val response = apiService.addBook(book)
            if (response.isSuccessful && response.body()?.success == true) {
                Result.success(response.body()?.data!!)
            } else {
                Result.failure(Exception("Gagal menambah buku"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    suspend fun updateBook(book: Book): Result<Book> {
        return try {
            val response = apiService.updateBook(book)
            if (response.isSuccessful && response.body()?.success == true) {
                Result.success(response.body()?.data!!)
            } else {
                Result.failure(Exception("Gagal mengupdate buku"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    suspend fun deleteBook(bookId: Int): Result<Boolean> {
        return try {
            val response = apiService.deleteBook(bookId)
            if (response.isSuccessful && response.body()?.success == true) {
                Result.success(true)
            } else {
                Result.failure(Exception("Gagal menghapus buku"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    suspend fun searchBooks(query: String): Result<List<Book>> {
        return try {
            val response = apiService.searchBooks(query)
            if (response.isSuccessful && response.body()?.success == true) {
                Result.success(response.body()?.data ?: emptyList())
            } else {
                Result.failure(Exception("Pencarian gagal"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
