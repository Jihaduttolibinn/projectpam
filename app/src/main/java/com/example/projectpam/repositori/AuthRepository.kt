package com.example.projectpam.repositori

import com.example.projectpam.apiservice.RetrofitClient
import com.example.projectpam.modeldata.LoginRequest
import com.example.projectpam.modeldata.LoginResponse
import com.example.projectpam.modeldata.User

class AuthRepository {

    private val apiService = RetrofitClient.apiService

    suspend fun login(username: String, password: String): Result<LoginResponse> {
        return try {
            // Mengirimkan permintaan login ke API menggunakan Retrofit
            val response = apiService.login(LoginRequest(username, password))
            if (response.isSuccessful && response.body() != null) {
                Result.success(response.body()!!)  // Mengembalikan hasil login sukses
            } else {
                Result.failure(Exception("Login gagal: ${response.message()}"))  // Jika login gagal, kirimkan error
            }
        } catch (e: Exception) {
            Result.failure(e) // Jika terjadi error (mis. koneksi), kirimkan exception
        }
    }

    suspend fun register(user: User): Result<LoginResponse> {
        return try {
            val response = apiService.register(user)
            if (response.isSuccessful && response.body() != null) {
                Result.success(response.body()!!)
            } else {
                Result.failure(Exception("Registrasi gagal: ${response.message()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}

