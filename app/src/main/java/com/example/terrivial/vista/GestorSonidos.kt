package com.example.terrivial.vista

import android.content.Context
import android.media.AudioAttributes
import android.media.SoundPool

object GestorSonidos {
    private lateinit var sp: SoundPool
    init {
        cargarSoundPool()
    }
    private fun cargarSoundPool() {
        sp = SoundPool.Builder().setAudioAttributes(
            AudioAttributes.Builder().setUsage(AudioAttributes.USAGE_GAME).setContentType(
                AudioAttributes.CONTENT_TYPE_SONIFICATION
            ).build()
        ).setMaxStreams(3).build()
    }
    fun queSuene(c : Context, soniduco : Int){
       val numerico = sp.load(c,soniduco,1)
        sp.setOnLoadCompleteListener { soundPool, _, _ ->
            soundPool.play(numerico, 1.0f, 1.0f, 1, 0, 1.0f)
        }

    }
}