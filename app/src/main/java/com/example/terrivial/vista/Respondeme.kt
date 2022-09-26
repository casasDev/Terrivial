package com.example.terrivial.vista

import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.graphics.drawable.ShapeDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.terrivial.R
import com.example.terrivial.modelo.Partida
import java.util.*
import java.util.Collections.shuffle

class Respondeme : AppCompatActivity() {
    private lateinit var pregunta: TextView
    private var partida = Partida.getInstance()
    private lateinit var respuestas : List<TextView>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.respondeme)
        pregunta = findViewById(R.id.pregunta)
        respuestas = listOf<TextView>(findViewById(R.id.respuesta0), findViewById(R.id.respuesta1),findViewById(R.id.respuesta2), findViewById(R.id.respuesta3))
        rellenar()
    }
    private fun rellenar(){
        var c = 0
        pregunta.text = partida.preguntaActual.enunciado
        shuffle(respuestas)
        partida.preguntaActual.respuestas.forEach {
            respuestas[c].text = it
            c++
        }
        respuestas.forEach{ r ->
            r.setOnClickListener{
               partida.esRespuestaCorrecta(r.text.toString())
                respuestas.forEach{
                    if(!it.equals(respuestas[3])) it.background.setTint(Color.RED)
                    else it.background.setTint(Color.GREEN)
                    it.setOnClickListener(null)
                }
            }

        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        respuestas.forEach{
            it.background.setTint(Color.WHITE)
        }
    }
    }