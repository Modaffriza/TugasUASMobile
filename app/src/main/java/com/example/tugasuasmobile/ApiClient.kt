package com.example.tugasuasmobile

object ApiClient {
    private const val BASE_URL = "https://ppbo-api.vercel.app/"

    val instance: ApiService by lazy {
        val retrofit = retrofit2.Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(retrofit2.converter.gson.GsonConverterFactory.create())
            .build()
        retrofit.create(ApiService::class.java)
    }
}