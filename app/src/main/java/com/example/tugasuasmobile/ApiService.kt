package com.example.tugasuasmobile

import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("https://ppbo-api.vercel.app/DxL1e/")
    fun getDpr(): Call<List<Dpr>>
}