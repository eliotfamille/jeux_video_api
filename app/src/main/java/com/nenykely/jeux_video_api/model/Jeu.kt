package com.nenykely.jeux_video_api.model

import com.google.gson.annotations.SerializedName

data class Jeu(
    val id: Int? = null,
    val titre: String,
    val plateforme: String,
    val developpeur: String,
    @SerializedName("annee_sortie") val anneeSortie: Int,
    val score: Int,
    // Champs Pokémon combinés
    val type: String = "Normal",
    @SerializedName("pv_actuel") val pvActuel: Int = 100,
    @SerializedName("pv_max") val pvMax: Int = 100,
    val niveau: Int = 1
)
