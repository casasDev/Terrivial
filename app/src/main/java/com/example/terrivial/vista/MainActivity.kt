package com.example.terrivial.vista

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ShareActionProvider
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.terrivial.R
import com.example.terrivial.modelo.Partida
import java.beans.PropertyChangeEvent
import java.beans.PropertyChangeListener

class MainActivity : AppCompatActivity(), PropertyChangeListener {
    private val partida = Partida.getInstance()
    private lateinit var jugar: Button
    private lateinit var scaleUp: Animation
    private lateinit var scaleDown: Animation
    private lateinit var tienda: Button
    private lateinit var monedos: TextView
    private lateinit var editor: SharedPreferences.Editor
    private lateinit var sharedPreferences: SharedPreferences

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        partida.addPropertyChangeListener(this)
        partida.llenarMapas(this)
        sharedPreferences = getSharedPreferences("DatosPalyer", Context.MODE_PRIVATE)
        editor = sharedPreferences.edit()
        monedos = findViewById(R.id.monedos)
        partida.monedas = sharedPreferences.getInt("monedas", 0)
        jugar = findViewById(R.id.jugar)
        tienda = findViewById(R.id.tienda)
        scaleUp = AnimationUtils.loadAnimation(this, R.anim.scale_up1)
        scaleDown = AnimationUtils.loadAnimation(this, R.anim.scale_down1)
        jugar.setOnClickListener {
            startActivity(Intent(this, MainActivity2::class.java))
        }
        tienda.setOnClickListener {
            startActivity(Intent(this, Tienda::class.java))
        }
        jugar.setOnTouchListener { view: View, motionEvent: MotionEvent ->
            onTouch(view, motionEvent)
        }
        tienda.setOnTouchListener { view: View, motionEvent: MotionEvent ->
            onTouch(view, motionEvent)
        }

    }

    private fun onTouch(view: View, motionEvent: MotionEvent): Boolean {
        if (motionEvent.action == MotionEvent.ACTION_DOWN) {
            view.startAnimation(scaleUp)
        } else if (motionEvent.action == MotionEvent.ACTION_UP)
            view.startAnimation(scaleDown)
        return false
    }

    override fun propertyChange(p0: PropertyChangeEvent?) {
        if (p0!!.propertyName.equals("monedos")) {
            editor.putInt("monedas", p0.newValue as Int)
            editor.commit()
            editor.apply()
            monedos.text = p0.newValue.toString() + " monedas"
        }
    }


}