package com.example.terrivial.vista.casino

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.gridlayout.widget.GridLayout
import com.example.terrivial.R
import com.example.terrivial.modelo.casino.Ahorcado
import java.beans.PropertyChangeEvent

class Ahorcadito : JuegoView() {
    private lateinit var palabra : TextView
    private lateinit var gridLetras : GridLayout
    private lateinit var listaNegra : TextView
    private val numFilas = 7
    private val numColumnas = 4
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        palabra = findViewById(R.id.palabro)
        gridLetras = findViewById(R.id.gridLetras)
        listaNegra = findViewById(R.id.listaNegra)
        this.juego = Ahorcado(this.getApuesta())
        listaNegra.text = listaNegra.text.toString() +"(0/"+(this.juego as Ahorcado).vidas+"): "
        this.juego.addPropertyChangeListener(this)
        palabra.text = this.juego.toString()
        generarBotones()
    }
    override fun getLayoutID(): Int {
        return R.layout.ahorcado_layout
    }
    private fun generarBotones(){
        gridLetras.columnCount = numColumnas
        gridLetras.rowCount = numFilas
        val abecedario = Ahorcado.getAbecedario()
        var b: Button?
        abecedario.forEach { i ->
            b = Button(this)
            b?.background?.setTint(Color.GRAY)
            b?.text = i.toString()
            b?.setOnClickListener{
                ejecutar(i.toString())
            }
            gridLetras.addView(b)
        }
    }
    @SuppressLint("SetTextI18n")
    override fun propertyChange(p0: PropertyChangeEvent?) {
        if(p0?.propertyName.equals("Letra acertada")){
            palabra.text = (p0?.oldValue).toString()
        }
        else if(p0?.propertyName.equals("Letra fallada")){
            if(listaNegra.text.toString().substring(16).contains((p0?.oldValue as Int).toChar())) Toast.makeText(this,"La lista negra está para que la mires bobo, ahira por listillo te la añado otra vez",Toast.LENGTH_LONG).show()
            val vidasActuales = p0.newValue as Int
            val intentos = Ahorcado.getVidasIniciales()-(vidasActuales)
            listaNegra.text = listaNegra.text.toString().replace((intentos-1).toString(),intentos.toString())
            listaNegra.text = listaNegra.text.toString() + (p0.oldValue as Int).toChar() + ", "
        }
        else super.propertyChange(p0)
    }
}