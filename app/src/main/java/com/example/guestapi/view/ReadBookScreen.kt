package com.example.guestapi.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.guestapi.viewmodel.BookViewModel

// Warna Kertas (Warm White / Cream) untuk pengalaman membaca yang nyaman
val PaperBackground = Color(0xFFFDF8E4)
val InkColor = Color(0xFF2D2D2D)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReadBookScreen(
    bookId: Int,
    onNavigateBack: () -> Unit,
    bookViewModel: BookViewModel = viewModel()
) {
    val selectedBook by bookViewModel.selectedBook.collectAsState()
    val isLoading by bookViewModel.isLoading.collectAsState()
    
    // Load book data
    LaunchedEffect(bookId) {
        bookViewModel.loadBook(bookId)
    }
    
    Scaffold(
        containerColor = PaperBackground // Set background color to paper
    ) { paddingValues ->
        if (isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
            }
        } else {
            selectedBook?.let { book ->
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                ) {
                    // Custom Minimalist Top Bar for Reading Mode
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(
                            onClick = onNavigateBack,
                            modifier = Modifier
                                .background(Color.Black.copy(alpha = 0.05f), CircleShape)
                        ) {
                            Icon(Icons.AutoMirrored.Filled.ArrowBack, "Kembali", tint = InkColor)
                        }
                        
                        Spacer(modifier = Modifier.width(16.dp))
                        
                        Text(
                            text = "Sedang Membaca",
                            style = MaterialTheme.typography.labelLarge,
                            color = InkColor.copy(alpha = 0.6f)
                        )
                    }

                    Divider(color = InkColor.copy(alpha = 0.1f))

                    // Content Scroll Area
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .verticalScroll(rememberScrollState())
                            .padding(horizontal = 24.dp, vertical = 32.dp)
                    ) {
                        // Title Section
                        Text(
                            text = book.judul,
                            style = MaterialTheme.typography.headlineMedium.copy(
                                fontFamily = FontFamily.Serif,
                                fontSize = 32.sp
                            ),
                            fontWeight = FontWeight.Bold,
                            color = InkColor,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth()
                        )
                        
                        Spacer(modifier = Modifier.height(16.dp))
                        
                        Text(
                            text = "Penulis : ${book.penulis}",
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontFamily = FontFamily.Serif,
                                fontStyle = FontStyle.Italic
                            ),
                            color = InkColor.copy(alpha = 0.7f),
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth()
                        )
                        
                        Spacer(modifier = Modifier.height(40.dp))
                        
                        // Decorative Element (Drop Cap or Divider)
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(1.dp)
                                .background(InkColor.copy(alpha = 0.2f))
                        )
                        
                        Spacer(modifier = Modifier.height(40.dp))
                        
                        // Book Body Text
                        Text(
                            text = book.buku,
                            style = MaterialTheme.typography.bodyMedium.copy(
                                fontFamily = FontFamily.Serif, // Serif font is key for "book feel"
                                fontSize = 18.sp,
                                lineHeight = 32.sp,
                                letterSpacing = 0.5.sp
                            ),
                            color = InkColor,
                            textAlign = TextAlign.Justify
                        )
                        
                        Spacer(modifier = Modifier.height(80.dp)) // Bottom padding
                        
                        Text(
                            text = "— Akhir —",
                            style = MaterialTheme.typography.labelMedium,
                            color = InkColor.copy(alpha = 0.4f),
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth()
                        )
                         Spacer(modifier = Modifier.height(40.dp))
                    }
                }
            }
        }
    }
}
