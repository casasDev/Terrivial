package com.example.terrivial.vista.casino

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.gridlayout.widget.GridLayout
import android.widget.TextView
import android.widget.Toast
import com.example.terrivial.R
import com.example.terrivial.modelo.casino.AdivinarNumero
import java.beans.PropertyChangeEvent
import java.util.stream.IntStream.*
class AdivinaNumero : JuegoView() {
    private lateinit var numero : TextView
    private lateinit var intentos : TextView
    private lateinit var botonesNumeros : GridLayout
    private val numFilas =3
    private val numColumnas = 4
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.juego = (AdivinarNumero(this.getApuesta()))
        this.juego.addPropertyChangeListener(this)
        numero = findViewById(R.id.adivina)
        intentos = findViewById(R.id.intentos)
        botonesNumeros = findViewById(R.id.numeros)
        intentos.text = getString(R.string.intentos) + (this.juego as AdivinarNumero).vidas
        generarBotones()
    }
    private fun generarBotones(){
        botonesNumeros.columnCount = numColumnas
        botonesNumeros.rowCount = numFilas
        range(0,10).forEach {
            val b = Button(this)
            b.text = it.toString()
            b.setOnClickListener {
                ejecutar(b.text.toString())
            }
            b.visibility = View.VISIBLE
            botonesNumeros.addView(b)
        }
    }
    override fun getLayoutID(): Int {
        return R.layout.adivinar_numero
    }

    override fun propertyChange(p0: PropertyChangeEvent?) {
        if(p0?.propertyName.equals("Numero incorrecto")){
            Toast.makeText(this,"La has cagado majo ese numero no era",Toast.LENGTH_SHORT).show()
            intentos.text = getString(R.string.intentos)+p0?.oldValue
        } else super.propertyChange(p0)
    }
}