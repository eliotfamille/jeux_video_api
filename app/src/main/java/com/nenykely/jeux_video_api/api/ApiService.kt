package com.nenykely.jeux_video_api.api

import com.nenykely.jeux_video_api.model.Jeu
import com.nenykely.jeux_video_api.model.Livre
import retrofit2.http.*

interface ApiService {
    @GET("jeus")
    suspend fun getJeus(): List<Jeu>

    @GET("livres")
    suspend fun getLivres(): List<Livre>

    @POST("jeus")
    suspend fun createJeu(@Body jeu: Jeu): Jeu

    @GET("jeus/{id}")
    suspend fun getJeu(@Path("id") id: Int): Jeu

    @PUT("jeus/{id}")
    suspend fun updateJeu(@Path("id") id: Int, @Body jeu: Jeu): Jeu

    @DELETE("jeus/{id}")
    suspend fun deleteJeu(@Path("id") id: Int)
}
