package com.example.guestapi.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.guestapi.modeldata.User
import com.example.guestapi.repositori.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LoginViewModel(private val repository: AuthRepository = AuthRepository()) : ViewModel() {

    // State untuk mengelola status login (Idle, Loading, Success, Error)
    private val _loginState = MutableStateFlow<LoginState>(LoginState.Idle)
    val loginState: StateFlow<LoginState> = _loginState

    // State untuk menyimpan informasi pengguna yang sedang login
    private val _currentUser = MutableStateFlow<User?>(null)
    val currentUser: StateFlow<User?> = _currentUser

    // State untuk registrasi
    private val _registerState = MutableStateFlow<LoginState>(LoginState.Idle)
    val registerState: StateFlow<LoginState> = _registerState

    fun register(user: User) {
        viewModelScope.launch {
            _registerState.value = LoginState.Loading
            try {
                val result = repository.register(user)
                result.onSuccess { response ->
                    if (response.success) {
                        _registerState.value = LoginState.Success(user)
                    } else {
                        _registerState.value = LoginState.Error(response.message)
                    }
                }.onFailure { error ->
                    _registerState.value = LoginState.Error(error.message ?: "Registrasi gagal")
                }
            } catch (e: Exception) {
                _registerState.value = LoginState.Error(e.message ?: "Registrasi gagal")
            }
        }
    }
    
    fun resetRegisterState() {
        _registerState.value = LoginState.Idle
    }

    // Fungsi untuk melakukan login
    fun login(username: String, password: String) {
        viewModelScope.launch {
            _loginState.value = LoginState.Loading // Menandakan bahwa login sedang diproses

            try {
                // Memanggil repository untuk login
                val result = repository.login(username, password)

                result.onSuccess { response ->
                if (response.success && response.data?.user != null) {
                    // Jika login berhasil, simpan data pengguna ke state dan ubah status login
                    val user = response.data.user
                    _currentUser.value = user
                    _loginState.value = LoginState.Success(user)
                } else {
                    // Jika login gagal, perbarui status login dengan pesan error
                    _loginState.value = LoginState.Error(response.message)
                }
            }.onFailure { error ->
                    // Jika ada kesalahan dalam proses login (misalnya error jaringan)
                    _loginState.value = LoginState.Error(error.message ?: "Login gagal")
                }
            } catch (e: Exception) {
                // Tangani error jaringan atau server
                _loginState.value = LoginState.Error(e.message ?: "Login gagal")
            }
        }
    }

    // Fungsi untuk melakukan logout
    fun logout() {
        _currentUser.value = null
        _loginState.value = LoginState.Idle // Kembalikan status login ke Idle setelah logout
    }
}

// State untuk menggambarkan status login
sealed class LoginState {
    object Idle : LoginState() // Status ketika aplikasi idle, belum login
    object Loading : LoginState() // Status ketika login sedang diproses
    data class Success(val user: User) : LoginState() // Status ketika login sukses
    data class Error(val message: String) : LoginState() // Status ketika login gagal
}
