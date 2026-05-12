package com.nenykely.jeux_video_api.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.nenykely.jeux_video_api.model.Livre
import com.nenykely.jeux_video_api.viewmodel.LivreViewModel

@Composable
fun LivreScreen(viewModel: LivreViewModel = viewModel()) {
    val livres by viewModel.livres.collectAsState()

    LazyColumn(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        items(livres) { livre ->
            LivreItem(livre)
        }
    }
}

@Composable
fun LivreItem(livre: Livre) {
    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 8.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = livre.title, style = MaterialTheme.typography.titleLarge)
            Text(text = "Par ${livre.author}", style = MaterialTheme.typography.bodyMedium)
            Text(text = livre.description, style = MaterialTheme.typography.bodySmall)
        }
    }
}
