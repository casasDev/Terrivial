package com.example.terrivial.vista


import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.terrivial.R
import com.example.terrivial.modelo.Entretenimiento
import com.example.terrivial.modelo.Partida

class MainActivity : AppCompatActivity() {
    private val partida = Partida.getInstance()
    private lateinit var jugar : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        partida.llenarMapas(this)
        jugar = findViewById(R.id.jugar)
        partida.actualizarPregunta("Entretenimiento")
        Log.d("OYEE", partida.preguntaActual.toString())
        jugar.setOnClickListener{

        }

    }

}