package com.example.guestapi.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.guestapi.modeldata.Book
import com.example.guestapi.repositori.BookRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class BookViewModel : ViewModel() {
    
    private val repository = BookRepository()
    
    private val _books = MutableStateFlow<List<Book>>(emptyList())
    val books: StateFlow<List<Book>> = _books
    
    private val _selectedBook = MutableStateFlow<Book?>(null)
    val selectedBook: StateFlow<Book?> = _selectedBook
    
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading
    
    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage
    
    private val _successMessage = MutableStateFlow<String?>(null)
    val successMessage: StateFlow<String?> = _successMessage
    
    fun loadBooks() {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null
            
            val result = repository.getAllBooks()
            result.onSuccess { bookList ->
                _books.value = bookList
            }.onFailure { error ->
                _errorMessage.value = error.message
            }
            
            _isLoading.value = false
        }
    }
    
    fun loadBook(bookId: Int) {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null
            
            val result = repository.getBook(bookId)
            result.onSuccess { book ->
                _selectedBook.value = book
            }.onFailure { error ->
                _errorMessage.value = error.message
            }
            
            _isLoading.value = false
        }
    }
    
    fun addBook(book: Book) {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null
            _successMessage.value = null
            
            val result = repository.addBook(book)
            result.onSuccess {
                _successMessage.value = "Buku berhasil ditambahkan"
                loadBooks() // Refresh list
            }.onFailure { error ->
                _errorMessage.value = error.message
            }
            
            _isLoading.value = false
        }
    }
    
    fun updateBook(book: Book) {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null
            _successMessage.value = null
            
            val result = repository.updateBook(book)
            result.onSuccess {
                _successMessage.value = "Buku berhasil diupdate"
                loadBooks() // Refresh list
            }.onFailure { error ->
                _errorMessage.value = error.message
            }
            
            _isLoading.value = false
        }
    }
    
    fun deleteBook(bookId: Int) {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null
            _successMessage.value = null
            
            val result = repository.deleteBook(bookId)
            result.onSuccess {
                _successMessage.value = "Buku berhasil dihapus"
                loadBooks() // Refresh list
            }.onFailure { error ->
                _errorMessage.value = error.message
            }
            
            _isLoading.value = false
        }
    }
    
    fun searchBooks(query: String) {
        if (query.isEmpty()) {
            loadBooks()
            return
        }
        
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null
            
            val result = repository.searchBooks(query)
            result.onSuccess { bookList ->
                _books.value = bookList
            }.onFailure { error ->
                _errorMessage.value = error.message
            }
            
            _isLoading.value = false
        }
    }
    
    fun clearMessages() {
        _errorMessage.value = null
        _successMessage.value = null
    }
}
