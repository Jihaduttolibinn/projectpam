package com.example.guestapi.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.guestapi.modeldata.Book
import com.example.guestapi.viewmodel.BookViewModel
import com.example.guestapi.viewmodel.LoginViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserHomeScreen(
    onNavigateToRead: (Int) -> Unit,
    onLogout: () -> Unit,
    bookViewModel: BookViewModel = viewModel(),
    loginViewModel: LoginViewModel = viewModel()
) {
    val books by bookViewModel.books.collectAsState()
    val isLoading by bookViewModel.isLoading.collectAsState()
    
    var searchQuery by remember { mutableStateOf("") }
    
    LaunchedEffect(Unit) {
        bookViewModel.loadBooks()
    }
    
    Scaffold { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(paddingValues)
        ) {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                 // Header Gradient
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(180.dp)
                        .background(
                            brush = Brush.verticalGradient(
                                colors = listOf(
                                    MaterialTheme.colorScheme.primary,
                                    MaterialTheme.colorScheme.primaryContainer
                                )
                            ),
                            shape = RoundedCornerShape(bottomStart = 32.dp, bottomEnd = 32.dp)
                        )
                ) {
                   Column(
                       modifier = Modifier
                           .fillMaxSize()
                           .padding(24.dp)
                   ) {
                       Row(
                           modifier = Modifier.fillMaxWidth(),
                           horizontalArrangement = Arrangement.SpaceBetween,
                           verticalAlignment = Alignment.CenterVertically
                       ) {
                            Column {
                                Text(
                                    text = "Selamat Datang!",
                                    style = MaterialTheme.typography.headlineMedium,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.onPrimary
                                )
                                Text(
                                    text = "Temukan buku favoritmu",
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.8f)
                                )
                            }
                           IconButton(
                               onClick = {
                                   loginViewModel.logout()
                                   onLogout()
                               },
                               modifier = Modifier
                                   .background(Color.White.copy(alpha = 0.2f), CircleShape)
                           ) {
                               Icon(Icons.Filled.ExitToApp, "Logout", tint = MaterialTheme.colorScheme.onPrimary)
                           }
                       }
                       
                       Spacer(modifier = Modifier.height(24.dp))
                       
                       // Search Field inside Header
                        OutlinedTextField(
                            value = searchQuery,
                            onValueChange = { 
                                searchQuery = it
                                if (it.isEmpty()) {
                                    bookViewModel.loadBooks()
                                }
                            },
                            placeholder = { Text("Cari buku...", color = Color.Gray) },
                            leadingIcon = { Icon(Icons.Filled.Search, null, tint = MaterialTheme.colorScheme.primary) },
                            trailingIcon = {
                                if (searchQuery.isNotEmpty()) {
                                    IconButton(onClick = {
                                        bookViewModel.searchBooks(searchQuery)
                                    }) {
                                        Icon(Icons.Filled.Search, "Cari", tint = MaterialTheme.colorScheme.primary)
                                    }
                                }
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(56.dp),
                            shape = RoundedCornerShape(28.dp),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedContainerColor = MaterialTheme.colorScheme.surface,
                                unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                                disabledContainerColor = MaterialTheme.colorScheme.surface,
                                focusedBorderColor = Color.Transparent,
                                unfocusedBorderColor = Color.Transparent
                            ),
                            singleLine = true
                        )
                   }
                }
                
                Spacer(modifier = Modifier.height(8.dp))

                // Content
                if (isLoading) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .weight(1f),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
                    }
                } else if (books.isEmpty()) {
                     Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .weight(1f),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(
                                text = "📚",
                                fontSize = 64.sp
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                            Text(
                                text = "Belum ada buku tersedia",
                                style = MaterialTheme.typography.titleMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                } else {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        modifier = Modifier.weight(1f),
                        contentPadding = PaddingValues(16.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        items(books) { book ->
                            BookItemUser(
                                book = book,
                                onClick = { onNavigateToRead(book.buku_id) }
                            )
                        }
                    }
                }
            }
        }
    }
}

// Helper to generate consistent colorful gradient covers
@Composable
fun getBookBrush(seed: String): Brush {
    val gradients = listOf(
        listOf(Color(0xFFEF9A9A), Color(0xFFEF5350)), // Red
        listOf(Color(0xFFCE93D8), Color(0xFFAB47BC)), // Purple
        listOf(Color(0xFF90CAF9), Color(0xFF42A5F5)), // Blue
        listOf(Color(0xFF80CBC4), Color(0xFF26A69A)), // Teal
        listOf(Color(0xFFFFCC80), Color(0xFFFFA726)), // Orange
        listOf(Color(0xFFBCAAA4), Color(0xFF8D6E63)), // Brown
        listOf(Color(0xFFB0BEC5), Color(0xFF78909C))  // Blue Grey
    )
    val index = kotlin.math.abs(seed.hashCode()) % gradients.size
    return Brush.linearGradient(
        colors = gradients[index],
        start = androidx.compose.ui.geometry.Offset(0f, 0f),
        end = androidx.compose.ui.geometry.Offset(100f, 100f)
    )
}

@Composable
fun BookItemUser(
    book: Book,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column(
            modifier = Modifier.padding(12.dp)
        ) {
            // Gradient Book Cover (Vertical)
            val coverBrush = getBookBrush(book.judul)
            
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(0.7f), // Standard book ratio
                shape = RoundedCornerShape(8.dp),
                shadowElevation = 4.dp
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(brush = coverBrush)
                ) {
                    // Spine effect
                    Box(
                        modifier = Modifier
                            .fillMaxHeight()
                            .width(6.dp)
                            .background(Color.Black.copy(alpha = 0.15f))
                            .align(Alignment.CenterStart)
                    )
                    
                    // Title Initial on Cover
                    Text(
                        text = book.judul.take(1).uppercase(),
                        style = MaterialTheme.typography.displayLarge.copy(fontSize = 48.sp),
                        color = Color.White.copy(alpha = 0.9f),
                        modifier = Modifier.align(Alignment.Center),
                        fontWeight = FontWeight.Bold
                    )
                    
                    // Genre Badge Overlay
                    Surface(
                        color = MaterialTheme.colorScheme.surface.copy(alpha = 0.9f),
                        shape = RoundedCornerShape(bottomStart = 8.dp),
                        modifier = Modifier.align(Alignment.TopEnd)
                    ) {
                        Text(
                            text = "Novel",
                            style = MaterialTheme.typography.labelSmall,
                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp),
                            color = MaterialTheme.colorScheme.onSurface 
                        )
                    }
                }
            }
            
            Spacer(modifier = Modifier.height(12.dp))
            
            // Title
            Text(
                text = book.judul,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                lineHeight = 20.sp
            )
            
            Spacer(modifier = Modifier.height(4.dp))
            
            // Author
            Text(
                text = book.penulis,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}
