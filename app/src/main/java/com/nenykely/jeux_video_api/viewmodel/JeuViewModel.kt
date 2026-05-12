package com.nenykely.jeux_video_api.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nenykely.jeux_video_api.api.RetrofitInstance
import com.nenykely.jeux_video_api.model.Jeu
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class JeuViewModel : ViewModel() {
    private val _jeuState = MutableStateFlow<Jeu?>(null)
    val jeuState: StateFlow<Jeu?> = _jeuState

    private val _listeJeus = MutableStateFlow<List<Jeu>>(emptyList())
    val listeJeus: StateFlow<List<Jeu>> = _listeJeus

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    init {
        chargerJeus()
    }

    fun chargerJeus() {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null
            try {
                val response = RetrofitInstance.api.getJeus()
                _listeJeus.value = response
                if (response.isNotEmpty() && _jeuState.value == null) {
                    _jeuState.value = response.first()
                }
            } catch (e: Exception) {
                e.printStackTrace()
                _errorMessage.value = "Erreur de connexion : ${e.localizedMessage}"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun selectionnerJeu(jeu: Jeu) {
        _jeuState.value = jeu
    }

    // LOGIQUE DE COMBAT ET SCORE
    
    fun attaquer(points: Int) {
        val jeu = _jeuState.value ?: return
        // On augmente le score et on réduit un peu les PV pour simuler un combat
        val nouveauJeu = jeu.copy(
            score = (jeu.score + points).coerceIn(0, 100),
            pvActuel = (jeu.pvActuel - 10).coerceAtLeast(0)
        )
        sauvegarderPartie(nouveauJeu)
    }

    fun utiliserObjet() {
        val jeu = _jeuState.value ?: return
        // Soigner : remonter les PV
        val nouveauJeu = jeu.copy(
            pvActuel = (jeu.pvActuel + 20).coerceAtMost(jeu.pvMax)
        )
        sauvegarderPartie(nouveauJeu)
    }

    private fun sauvegarderPartie(jeu: Jeu) {
        viewModelScope.launch {
            try {
                jeu.id?.let { id ->
                    val updated = RetrofitInstance.api.updateJeu(id, jeu)
                    _jeuState.value = updated
                    chargerJeus()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
    
    fun nouvellePartie(nom: String, plateforme: String) {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null
            try {
                val nouveau = Jeu(
                    titre = nom,
                    plateforme = plateforme,
                    developpeur = "Nintendo",
                    anneeSortie = 2024,
                    score = 0,
                    type = "Electrique",
                    pvActuel = 100,
                    pvMax = 100,
                    niveau = 1
                )
                val cree = RetrofitInstance.api.createJeu(nouveau)
                _jeuState.value = cree
                chargerJeus()
            } catch (e: Exception) {
                e.printStackTrace()
                _errorMessage.value = "Impossible de créer la partie : ${e.localizedMessage}"
            } finally {
                _isLoading.value = false
            }
        }
    }
}
