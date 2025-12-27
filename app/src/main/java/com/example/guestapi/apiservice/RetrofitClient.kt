package com.example.guestapi.apiservice

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    // Ganti dengan URL server Anda jika menggunakan perangkat fisik (bukan emulator)
    private const val BASE_URL = "http://10.0.2.2/bukuonline/api/"  // URL untuk emulator Android (localhost)

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val apiService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}
