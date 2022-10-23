package com.example.terrivial.vista

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import com.example.terrivial.R
import com.example.terrivial.modelo.Partida
import java.util.Collections.shuffle

class Respondeme : AppCompatActivity() {
    private lateinit var pregunta: TextView
    private var partida = Partida.getInstance()
    private lateinit var respuestas : List<TextView>
    private lateinit var scaleUp : Animation
    private lateinit var scaleDown : Animation
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.respondeme)
        pregunta = findViewById(R.id.pregunta)
        scaleDown = AnimationUtils.loadAnimation(this,R.anim.scale_down2)
        scaleUp = AnimationUtils.loadAnimation(this, R.anim.scale_up2)
        respuestas = listOf<TextView>(findViewById(R.id.respuesta0), findViewById(R.id.respuesta1),findViewById(R.id.respuesta2), findViewById(R.id.respuesta3))
        rellenar()
    }
    @SuppressLint("ClickableViewAccessibility")
    private fun rellenar(){
        var c = 0
        YoYo.with(Techniques.RotateInUpLeft).duration(500).playOn(pregunta)
        pregunta.text = partida.preguntaActual.enunciado
        shuffle(respuestas)
        partida.preguntaActual.respuestas.forEach {
            if(c in (0..3)) {
                respuestas[c].text = it
                c++
            }
        }
        respuestas.forEach{
                r ->
            YoYo.with(Techniques.RotateInUpLeft).duration(500).playOn(r)
            r.setOnClickListener{
              if(!partida.esRespuestaCorrecta(r.text.toString())) YoYo.with(Techniques.Shake).duration(500).playOn(r)
                respuestas.forEach{
                    if(it != respuestas[3]) it.background.setTint(Color.RED)
                    else it.background.setTint(Color.GREEN)
                    it.setOnClickListener(null)
                    it.setOnTouchListener(null)
                }
            }
            r.setOnTouchListener{
                    view : View, event : MotionEvent ->
                if(event.action == MotionEvent.ACTION_DOWN && view.id == r.id) {
                    r.startAnimation(scaleDown)
                }
                else if(event.action == MotionEvent.ACTION_UP && view.id == r.id)
                    r.startAnimation(scaleUp)
                false
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        respuestas.forEach{
            it.background.setTint(Color.WHITE)
        }
        if(respuestas[0].hasOnClickListeners()) partida.esRespuestaCorrecta("")
    }
    }