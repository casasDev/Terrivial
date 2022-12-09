package com.example.terrivial.vista

import android.graphics.Color
import android.view.View
import java.util.*
import java.util.stream.IntStream

class FiftyFIfty : Strategy {
    override fun accion(lista:List<View>){
        val numRandom = Random().nextInt(3)
        IntStream.range(0,lista.size-1).boxed().filter{
            it != numRandom
        }.forEach{
            lista[it].isEnabled = false
            lista[it].background.setTint(Color.DKGRAY)
        }
    }
}