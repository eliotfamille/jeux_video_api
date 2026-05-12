package com.nenykely.jeux_video_api.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.nenykely.jeux_video_api.viewmodel.JeuViewModel

@Composable
fun JeuScreen(viewModel: JeuViewModel = viewModel()) {
    val jeu by viewModel.jeuState.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()

    if (jeu == null) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (isLoading) {
                CircularProgressIndicator()
                Spacer(modifier = Modifier.height(16.dp))
                Text("Chargement...")
            } else {
                errorMessage?.let {
                    Text(text = it, color = Color.Red, modifier = Modifier.padding(16.dp))
                }
                
                Button(onClick = { viewModel.nouvellePartie("Pikachu", "Switch") }) {
                    Text("Démarrer une nouvelle partie")
                }
                
                Button(
                    onClick = { viewModel.chargerJeus() },
                    modifier = Modifier.padding(top = 8.dp)
                ) {
                    Text("Réessayer de charger")
                }
            }
        }
    } else {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = jeu!!.titre, style = MaterialTheme.typography.headlineLarge)
            Text(text = "Plateforme: ${jeu!!.plateforme} | Type: ${jeu!!.type}", style = MaterialTheme.typography.bodyLarge)
            Text(text = "Niveau: ${jeu!!.niveau} | Score: ${jeu!!.score}", style = MaterialTheme.typography.titleMedium)

            Spacer(modifier = Modifier.height(24.dp))

            // BARRE DE PROGRESSION PV
            val pvProgress = jeu!!.pvActuel.toFloat() / jeu!!.pvMax.toFloat()
            Text(text = "PV: ${jeu!!.pvActuel} / ${jeu!!.pvMax}")
            LinearProgressIndicator(
                progress = pvProgress,
                modifier = Modifier.fillMaxWidth().height(12.dp),
                color = if (pvProgress > 0.5f) Color.Green else if (pvProgress > 0.2f) Color.Yellow else Color.Red
            )

            Spacer(modifier = Modifier.height(16.dp))

            // BARRE DE PROGRESSION SCORE
            val scoreProgress = jeu!!.score.toFloat() / 100f
            Text(text = "Score Total (Objectif 100)")
            LinearProgressIndicator(
                progress = scoreProgress,
                modifier = Modifier.fillMaxWidth().height(12.dp),
                color = Color.Blue
            )

            Spacer(modifier = Modifier.height(32.dp))

            // ACTIONS
            Row(modifier = Modifier.fillMaxWidth()) {
                Button(onClick = { viewModel.attaquer(10) }, modifier = Modifier.weight(1f).padding(4.dp)) {
                    Text("Attaquer (+10)")
                }
                Button(onClick = { viewModel.utiliserObjet() }, modifier = Modifier.weight(1f).padding(4.dp), colors = ButtonDefaults.buttonColors(containerColor = Color.Magenta)) {
                    Text("Soigner (Objet)")
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
            
            Button(
                onClick = { viewModel.chargerJeus() },
                modifier = Modifier.padding(top = 16.dp)
            ) {
                Text("Actualiser")
            }
        }
    }
}
