package com.example.terrivial.vista.casino

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import com.example.terrivial.R
import com.example.terrivial.modelo.casino.Minijuego
import java.beans.PropertyChangeEvent
import java.beans.PropertyChangeListener

abstract class JuegoView : AppCompatActivity(), PropertyChangeListener {
    open lateinit var juego : Minijuego
    private var apuesta =0
    private lateinit var contenedor:ViewGroup
    private lateinit var inflater : LayoutInflater
    private lateinit var mensajeFinal : TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.juego_view)
        contenedor = findViewById(R.id.contenedor_juego)
        inflater = LayoutInflater.from(this)
        contenedor.addView(inflater.inflate(getLayoutID(),null))
        mensajeFinal = findViewById(R.id.mensajeFinMinijuego)
    }
    protected fun getApuesta():Int{
        return this.apuesta
    }
    protected abstract fun getLayoutID() : Int
    override fun propertyChange(p0: PropertyChangeEvent?) {
        contenedor.visibility = View.GONE
        mensajeFinal.visibility = View.VISIBLE
        if(p0?.propertyName.equals("Apuesta lograda")){
            mensajeFinal.text = getString(R.string.apuesta_lograda)
        } else if(p0?.propertyName.equals("Perdiste")){
            mensajeFinal.text = getString(R.string.perdiste)
        }
    }
}