package com.example.projectpam.view

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.projectpam.repositori.AuthRepository
import com.example.projectpam.ui.theme.ModernCyan
import com.example.projectpam.ui.theme.ModernIndigo
import com.example.projectpam.ui.theme.ModernViolet
import com.example.projectpam.viewmodel.LoginState
import com.example.projectpam.viewmodel.LoginViewModel
import com.example.projectpam.viewmodel.LoginViewModelFactory
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    onLoginSuccess: (String) -> Unit,
    onNavigateToRegister: () -> Unit,
    viewModel: LoginViewModel = viewModel(factory = LoginViewModelFactory(AuthRepository()))
) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val loginState by viewModel.loginState.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    LaunchedEffect(loginState) {
        if (loginState is LoginState.Success) {
            val user = (loginState as LoginState.Success).user
            scope.launch {
                snackbarHostState.showSnackbar("Selamat Datang, ${user.username}!")
                onLoginSuccess(user.role)
            }
        }
    }

    Scaffold(
        snackbarHost = {
            SnackbarHost(snackbarHostState) { data ->
                Card(
                    modifier = Modifier
                        .padding(16.dp)
                        .wrapContentWidth(),
                    shape = RoundedCornerShape(24.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.95f),
                        contentColor = MaterialTheme.colorScheme.onSurfaceVariant
                    ),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Text(
                        text = data.visuals.message,
                        modifier = Modifier.padding(horizontal = 24.dp, vertical = 12.dp),
                        style = MaterialTheme.typography.bodyMedium.copy(
                             fontFamily = FontFamily.Default,
                             fontWeight = FontWeight.Medium,
                             letterSpacing = 0.sp,
                             lineHeight = 20.sp
                        )
                    )
                }
            }
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(paddingValues)
        ) {
            // Dynamic Mesh Gradient Background
            Box(modifier = Modifier.fillMaxSize()) {
                // Cyan Glow (Matching Register)
                Box(
                    modifier = Modifier
                        .size(450.dp)
                        .offset(x = (-120).dp, y = (-120).dp)
                        .background(
                            brush = Brush.radialGradient(
                                colors = listOf(
                                    ModernCyan.copy(alpha = 0.15f),
                                    Color.Transparent
                                )
                            ),
                            shape = CircleShape
                        )
                )
                // Indigo Glow
                Box(
                    modifier = Modifier
                        .size(400.dp)
                        .align(Alignment.BottomEnd)
                        .offset(x = 100.dp, y = 100.dp)
                        .background(
                            brush = Brush.radialGradient(
                                colors = listOf(
                                    ModernIndigo.copy(alpha = 0.15f),
                                    Color.Transparent
                                )
                            ),
                            shape = CircleShape
                        )
                )
            }

            // Glassy Header with vibrant gradient
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                ModernIndigo,
                                ModernCyan.copy(alpha = 0.7f),
                                Color.Transparent
                            )
                        )
                    )
                    .clip(RoundedCornerShape(bottomStart = 80.dp, bottomEnd = 80.dp))
            ) {
                // Floating accent
                Box(
                    modifier = Modifier
                        .size(120.dp)
                        .offset(x = 40.dp, y = 30.dp)
                        .background(Color.White.copy(alpha = 0.1f), CircleShape)
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.TopCenter)
                    .padding(top = 70.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Surface(
                    modifier = Modifier.size(90.dp),
                    shape = RoundedCornerShape(24.dp),
                    color = Color.White.copy(alpha = 0.2f),
                    border = BorderStroke(1.dp, Color.White.copy(alpha = 0.3f))
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Text(text = "📚", fontSize = 54.sp)
                    }
                }
                
                Spacer(modifier = Modifier.height(16.dp))
                
                Text(
                    text = "Buku Online",
                    style = MaterialTheme.typography.headlineMedium.copy(
                        fontWeight = FontWeight.ExtraBold,
                        letterSpacing = 2.sp,
                        color = Color.White
                    )
                )
                Text(
                    text = "",
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = Color.White.copy(alpha = 0.7f),
                        letterSpacing = 1.sp
                    )
                )
            }

            // High-End Glassmorphism Card
            Card(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(top = 220.dp)
                    .padding(horizontal = 24.dp)
                    .fillMaxWidth(),
                elevation = CardDefaults.cardElevation(defaultElevation = 20.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.98f)
                ),
                shape = RoundedCornerShape(32.dp),
                border = BorderStroke(
                    1.dp, 
                    Brush.linearGradient(
                        colors = listOf(
                            ModernIndigo.copy(alpha = 0.2f),
                            ModernCyan.copy(alpha = 0.1f)
                        )
                    )
                )
            ) {
                Column(
                    modifier = Modifier
                        .padding(32.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Masuk Ke Akun",
                        style = MaterialTheme.typography.headlineSmall.copy(
                            fontWeight = FontWeight.Black,
                            color = ModernIndigo
                        )
                    )
                    
                    Spacer(modifier = Modifier.height(32.dp))

                    // Username with refined design
                    OutlinedTextField(
                        value = username,
                        onValueChange = { username = it },
                        label = { Text("Username") },
                        leadingIcon = { 
                            Icon(
                                Icons.Filled.Person, 
                                contentDescription = null, 
                                tint = ModernIndigo
                            ) 
                        },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(20.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = ModernIndigo,
                            unfocusedBorderColor = MaterialTheme.colorScheme.outline.copy(alpha = 0.3f),
                            focusedContainerColor = ModernIndigo.copy(alpha = 0.05f),
                            unfocusedContainerColor = Color.Transparent
                        ),
                        enabled = loginState !is LoginState.Loading
                    )

                    Spacer(modifier = Modifier.height(18.dp))

                    // Password with refined design
                    OutlinedTextField(
                        value = password,
                        onValueChange = { password = it },
                        label = { Text("Password") },
                        leadingIcon = { 
                            Icon(
                                Icons.Filled.Lock, 
                                contentDescription = null, 
                                tint = ModernIndigo
                            ) 
                        },
                        singleLine = true,
                        visualTransformation = PasswordVisualTransformation(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(20.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = ModernIndigo,
                            unfocusedBorderColor = MaterialTheme.colorScheme.outline.copy(alpha = 0.3f),
                            focusedContainerColor = ModernIndigo.copy(alpha = 0.05f),
                            unfocusedContainerColor = Color.Transparent
                        ),
                        enabled = loginState !is LoginState.Loading
                    )

                    Spacer(modifier = Modifier.height(36.dp))

                    // Next-Gen Button with Inner Glow simulation
                    Button(
                        onClick = {
                            if (username.isNotBlank() && password.isNotBlank()) {
                                viewModel.login(username, password)
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(60.dp),
                        enabled = loginState !is LoginState.Loading && username.isNotBlank() && password.isNotBlank(),
                        shape = RoundedCornerShape(20.dp),
                        contentPadding = PaddingValues(0.dp),
                        elevation = ButtonDefaults.buttonElevation(
                            defaultElevation = 8.dp,
                            pressedElevation = 2.dp
                        )
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(
                                    brush = Brush.horizontalGradient(
                                        colors = listOf(ModernIndigo, ModernCyan)
                                    )
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            if (loginState is LoginState.Loading) {
                                CircularProgressIndicator(
                                    modifier = Modifier.size(28.dp),
                                    color = Color.White,
                                    strokeWidth = 3.dp
                                )
                            } else {
                                Text("MASUK", fontWeight = FontWeight.ExtraBold, letterSpacing = 2.sp)
                            }
                        }
                    }

                    if (loginState is LoginState.Error) {
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = (loginState as LoginState.Error).message,
                            color = MaterialTheme.colorScheme.error,
                            style = MaterialTheme.typography.bodySmall,
                            modifier = Modifier
                                .background(
                                    MaterialTheme.colorScheme.errorContainer.copy(alpha = 0.7f),
                                    shape = RoundedCornerShape(8.dp)
                                )
                                .padding(12.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(32.dp))

                    HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f))

                    Spacer(modifier = Modifier.height(24.dp))

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Belum punya akun? ", 
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Text(
                            text = "Daftar",
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.Bold,
                            color = ModernIndigo,
                            modifier = Modifier.clickable { onNavigateToRegister() }
                        )
                    }
                }
            }
        }
        }
    }

