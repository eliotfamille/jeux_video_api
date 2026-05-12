package com.nenykely.jeux_video_api.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    // 10.0.2.2 est l'adresse pour accéder au localhost de la machine hôte depuis l'émulateur Android
    private const val BASE_URL = "http://192.168.2.109:8000/api/"

    val api: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}
