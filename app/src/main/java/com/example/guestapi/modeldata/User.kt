package com.example.guestapi.modeldata

data class User(
    val user_id: Int = 0,
    val username: String,
    val password: String, // Added password field
    val email: String? = null,
    val role: String = "user" // "admin" atau "user"
)

data class LoginRequest(
    val username: String,
    val password: String
)

data class LoginData(
    val user: User
)

data class LoginResponse(
    val success: Boolean,
    val message: String,
    val data: LoginData? = null
)
