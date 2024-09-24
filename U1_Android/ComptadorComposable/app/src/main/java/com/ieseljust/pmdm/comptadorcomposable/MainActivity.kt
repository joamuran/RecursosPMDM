package com.ieseljust.pmdm.comptadorcomposable

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ieseljust.pmdm.comptadorcomposable.ui.theme.ComptadorComposableTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            // Definirem el contingut en una composable a banda
            ComptadorApp()
        }
    }
}


@Composable
fun ComptadorApp() {

    // Definim la propietat comptador com a rememberSaveble, per a que
    // mantinga l'esta entre rotacions de pantalla
    var comptador by rememberSaveable { mutableStateOf(0) }

    // Si la definim nomes com a remember, no guardara l'estat
    // en canvis d'orientacio
    // var comptador by remember { mutableStateOf(0) }

    ComptadorComposableTheme {
        // Definim l'esquelet amb un Scaffold
        Scaffold(modifier = Modifier.fillMaxSize()) { _ ->
            // El contingut s'organitzara en forma de columna
            Column(
                // Deifinim l'aliniacio horitzontal i vertical
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                // fem que ocupe la pantalla ocmpleta
                modifier = Modifier.fillMaxSize()
            ) { // Lambda que defineix el contingut, en base a altres composables:

                // - Un text
                Text(text = comptador.toString(), fontSize = 178.sp)

                // - Un espaiador
                Spacer(modifier = Modifier.height(16.dp))

                // - Un boto per incrementar el comptador
                Button(onClick = { comptador++ }) {
                    Text(text = "+", fontSize = 34.sp)
                }

                // Altre espaiador
                Spacer(modifier = Modifier.height(16.dp))

                // Altra funcio composable amb el boto per
                // obrir la segona activitat
                OpenActivityButton(comptador)

            }
        }
    }

}

@Composable
fun OpenActivityButton(comptador: Int) {
    val context = LocalContext.current
    Button(onClick = {
        val intent = Intent(context, MostraComptadorActivity::class.java).apply {
            putExtra("comptador", comptador)
        }
        context.startActivity(intent)
    }) {
        Text(text = "Open Activity")
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ComptadorApp()
}