package com.ieseljust.pmdm.comptadorMVVM

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer

class MainActivity : AppCompatActivity() {

    // Obtenim la referència al ViewModel delegant la instanciació
    // a la funció d'extensió viewModels.
    private val comptadorViewModel: ComptadorViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Enllacem la vista
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Establim les referencies a les diferents vistes de la interficie
        val textViewContador = findViewById<TextView>(R.id.textViewComptador)
        val btAdd = findViewById<Button>(R.id.btAdd)
        val btOpen = findViewById<Button>(R.id.btOpen)

        // Definim els observadors als LiveData. Quan el LiveData canvie de valor,
        // es dispararà el callback corresponent
        comptadorViewModel.comptador.observe(this, Observer { comptador ->
            textViewContador.text = comptador.toString()
        })

        // Quan es prem al boto de sumar, farem us del metode
        // incrementaComptador del ViewMOdel.
        btAdd.setOnClickListener {
            comptadorViewModel.incrementarComptador()
        }

        btOpen.setOnClickListener {
            Intent(baseContext, MostraComptadorActivity::class.java).apply {
                putExtra("comptador", comptadorViewModel.comptador.value)
                startActivity(this)
            }
        }
    }
}