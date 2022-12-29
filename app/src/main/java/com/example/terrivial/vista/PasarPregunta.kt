package com.example.terrivial.vista

import android.graphics.Color
import android.view.View
import com.example.terrivial.modelo.Partida

class PasarPregunta : Strategy{
    override fun accion(lista: List<View>) {
        lista.forEach{
            it.background.setTint(Color.WHITE)
        }
        Partida.getInstance().actualizarPregunta(Partida.getInstance().categoriaActual.nombre)
    }
}