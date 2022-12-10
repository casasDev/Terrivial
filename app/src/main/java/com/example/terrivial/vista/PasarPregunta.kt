package com.example.terrivial.vista

import android.app.Activity
import android.content.Intent
import android.view.View
import com.example.terrivial.modelo.Partida

class PasarPregunta : Strategy{
    override fun accion(lista: List<View>) {
        (lista[0].context as Activity).finish()
       Partida.getInstance().actualizarPregunta(Partida.getInstance().categoriaActual.nombre)
       lista[0].context.startActivity(Intent(lista[0].context, Respondeme::class.java))
    }

}