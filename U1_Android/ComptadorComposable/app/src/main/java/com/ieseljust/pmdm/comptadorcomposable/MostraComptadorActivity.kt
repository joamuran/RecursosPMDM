package com.ieseljust.pmdm.comptadorcomposable

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ieseljust.pmdm.comptadorcomposable.ui.theme.ComptadorComposableTheme

class MostraComptadorActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            // Carreguem el valor del comptador a partir de la Intent
            val comptador = intent.getIntExtra("comptador", 0)

            // I invoquem la funcio composable MostraComptadorScreen,
            // proporcionant-li el valor del comptador, i una funcio lambda
            // que sera la que utilitzem com a callback en el boto de tornar
            MostraComptadorScreen(comptador) {
                finish()
            }
        }
    }
}

@Composable
fun MostraComptadorScreen(comptador: Int, onBackClick: () -> Unit) {
    ComptadorComposableTheme {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Text(text = comptador.toString(), fontSize = 96.sp)
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = onBackClick) {
                Text(text = "Tornar")
            }
        }
    }
}
