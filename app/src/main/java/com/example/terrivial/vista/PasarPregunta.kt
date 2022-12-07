package com.example.terrivial.vista

import android.app.Activity
import android.content.Intent
import android.provider.Telephony
import android.view.View
import com.example.terrivial.modelo.Partida

class PasarPregunta : Strategy{
    override fun accion(lista: List<View>) {
       Partida.getInstance().actualizarPregunta(Partida.getInstance().categoriaActual.nombre)
       lista[0].context.startActivity(Intent(lista[0].context, Respondeme::class.java))
        (lista[0].context as Activity).finish()
    }

}