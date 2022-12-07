package com.example.terrivial.vista

import android.view.MotionEvent
import android.view.View
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import java.util.stream.IntStream

class RespuestaCorrecta : Strategy{
    override fun accion(lista: List<View>) {
        YoYo.with(Techniques.Pulse).duration(500).playOn(lista[lista.size-1])
        lista[lista.size-1].performClick()
    }
}