package com.example.terrivial.vista

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import com.example.terrivial.R
import com.example.terrivial.modelo.Partida
import com.example.terrivial.modelo.Potenciador
import java.beans.PropertyChangeEvent
import java.beans.PropertyChangeListener
import java.util.*
import java.util.Collections.shuffle

class Respondeme : AppCompatActivity(), PropertyChangeListener {
    private lateinit var pregunta: TextView
    private var partida = Partida.getInstance()
    private lateinit var respuestas : List<TextView>
    private lateinit var scaleUp : Animation
    private lateinit var scaleDown : Animation
    private lateinit var atras : ImageButton
    private lateinit var strategy: Strategy
    private lateinit var recyclerPotenciador : RecyclerView
    private lateinit var botonesPotenciador: AdaptadorRecycler
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.respondeme)
        Partida.getInstance().addPropertyChangeListener(this)
        atras = findViewById(R.id.atras)
        atras.background.setTint(partida.categoriaActual.color)
        pregunta = findViewById(R.id.pregunta)
        recyclerPotenciador = findViewById(R.id.botonesPoten)
        recyclerPotenciador.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        botonesPotenciador = AdaptadorRecycler()
        recyclerPotenciador.adapter = botonesPotenciador
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
                YoYo.with(Techniques.SlideInLeft).duration(250).playOn(atras)
                atras.visibility = View.VISIBLE
            }
            r.setOnTouchListener{
                    _: View, event : MotionEvent ->
                if(event.action == MotionEvent.ACTION_DOWN) {
                    r.startAnimation(scaleDown)
                }
                else if(event.action == MotionEvent.ACTION_UP)
                    r.startAnimation(scaleUp)
                false
            }
            atras.setOnClickListener{
                onBackPressed()
            }
            atras.setOnTouchListener { _: View, motionEvent : MotionEvent->
                if(motionEvent.action == MotionEvent.ACTION_DOWN) {
                   atras.startAnimation(scaleDown)
                }
                else if(motionEvent.action == MotionEvent.ACTION_UP)
                    atras.startAnimation(scaleUp)
                false
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        cambiarColor(Color.WHITE)
        if(respuestas[0].hasOnClickListeners()) partida.esRespuestaCorrecta("")
        finish()
    }
    private fun cambiarColor(pColor : Int){
        respuestas.forEach{
            it.background.setTint(pColor)
        }
    }
    private fun setStrategy(pot : String){
        this.strategy = Arrays.stream(Potenciador.values()).filter {
            pot == it.nombre
        }.findFirst().orElse(null).strategy
    }
    override fun propertyChange(p0: PropertyChangeEvent?) {
        if(p0?.propertyName.equals("Accion")){
            setStrategy(p0?.oldValue as String)
            strategy.accion(respuestas)
        }
    }
}