package com.example.terrivial.vista

import android.app.Activity
import android.content.Intent
import android.view.View
import com.example.terrivial.modelo.Partida

class PasarPregunta : Strategy{
    override fun accion(lista: List<View>) {
        Partida.getInstance().actualizarPregunta(Partida.getInstance().categoriaActual.nombre)
       //TODO(not implemented yet)
    }
}