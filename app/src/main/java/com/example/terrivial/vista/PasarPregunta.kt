package com.example.terrivial.vista

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.view.View
import android.widget.TextView
import com.example.terrivial.modelo.Partida
import java.util.stream.IntStream

class PasarPregunta : Strategy{
    override fun accion(lista: List<View>) {
        lista.forEach{
            it.background.setTint(Color.WHITE)
        }
        Partida.getInstance().actualizarPregunta(Partida.getInstance().categoriaActual.nombre)
    }
}