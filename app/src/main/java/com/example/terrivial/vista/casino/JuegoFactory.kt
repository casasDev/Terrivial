package com.example.terrivial.vista.casino

import android.content.Context
import android.content.Intent

object JuegoFactory {

    fun generate(juego : String, c : Context) : Intent {
        if (juego == "Ahorcado") {
            return Intent(c, Ahorcadito::class.java)
        }
        else if(juego == "Adivinar el número"){
            return Intent(c,AdivinaNumero::class.java)
        }
        else throw java.lang.Exception("El minijuego no existe")
    }
}