package com.nenykely.jeux_video_api.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nenykely.jeux_video_api.api.RetrofitInstance
import com.nenykely.jeux_video_api.model.Livre
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LivreViewModel : ViewModel() {
    private val _livres = MutableStateFlow<List<Livre>>(emptyList())
    val livres: StateFlow<List<Livre>> = _livres

    init {
        fetchLivres()
    }

    private fun fetchLivres() {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.getLivres()
                _livres.value = response
            } catch (e: Exception) {
                // Handle error
                e.printStackTrace()
            }
        }
    }
}
