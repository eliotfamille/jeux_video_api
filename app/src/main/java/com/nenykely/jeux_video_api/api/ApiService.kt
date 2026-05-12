package com.nenykely.jeux_video_api.api

import com.nenykely.jeux_video_api.model.Livre
import retrofit2.http.GET

interface ApiService {
    @GET("livres")
    suspend fun getLivres(): List<Livre>
}
