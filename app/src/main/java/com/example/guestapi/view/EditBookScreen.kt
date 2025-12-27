package com.example.guestapi.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.guestapi.modeldata.Book
import com.example.guestapi.viewmodel.BookViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditBookScreen(
    bookId: Int,
    onNavigateBack: () -> Unit,
    bookViewModel: BookViewModel = viewModel()
) {
    var judul by remember { mutableStateOf("") }
    var penulis by remember { mutableStateOf("") }
    var isiBuku by remember { mutableStateOf("") }
    var isInitialized by remember { mutableStateOf(false) }
    
    val selectedBook by bookViewModel.selectedBook.collectAsState()
    val isLoading by bookViewModel.isLoading.collectAsState()
    val successMessage by bookViewModel.successMessage.collectAsState()
    
    // Load book data
    LaunchedEffect(bookId) {
        bookViewModel.loadBook(bookId)
    }
    
    // Initialize form with book data
    LaunchedEffect(selectedBook) {
        selectedBook?.let { book ->
            if (!isInitialized) {
                judul = book.judul
                penulis = book.penulis
                isiBuku = book.buku
                isInitialized = true
            }
        }
    }
    
    // Navigate back on success
    LaunchedEffect(successMessage) {
        if (successMessage != null) {
            onNavigateBack()
        }
    }
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Edit Buku", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, "Kembali")
                    }
                }
            )
        }
    ) { paddingValues ->
        if (!isInitialized && isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                // Judul Field
                OutlinedTextField(
                    value = judul,
                    onValueChange = { judul = it },
                    label = { Text("Judul Buku") },
                    modifier = Modifier.fillMaxWidth(),
                    enabled = !isLoading,
                    singleLine = true
                )
                
                Spacer(modifier = Modifier.height(16.dp))
                
                // Penulis Field
                OutlinedTextField(
                    value = penulis,
                    onValueChange = { penulis = it },
                    label = { Text("Penulis") },
                    modifier = Modifier.fillMaxWidth(),
                    enabled = !isLoading,
                    singleLine = true
                )
                
                Spacer(modifier = Modifier.height(16.dp))
                
                // Isi Buku Field
                OutlinedTextField(
                    value = isiBuku,
                    onValueChange = { isiBuku = it },
                    label = { Text("Isi Buku") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp),
                    enabled = !isLoading,
                    maxLines = 15
                )
                
                Spacer(modifier = Modifier.height(24.dp))
                
                // Update Button
                Button(
                    onClick = {
                        if (judul.isNotBlank() && penulis.isNotBlank() && isiBuku.isNotBlank()) {
                            bookViewModel.updateBook(
                                Book(
                                    buku_id = bookId,
                                    judul = judul,
                                    penulis = penulis,
                                    buku = isiBuku
                                )
                            )
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    enabled = !isLoading && 
                             judul.isNotBlank() && 
                             penulis.isNotBlank() && 
                             isiBuku.isNotBlank()
                ) {
                    if (isLoading) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(24.dp),
                            color = MaterialTheme.colorScheme.onPrimary
                        )
                    } else {
                        Text("Update Buku", style = MaterialTheme.typography.titleMedium)
                    }
                }
            }
        }
    }
}
