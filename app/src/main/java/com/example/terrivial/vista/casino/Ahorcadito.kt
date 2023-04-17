package com.example.terrivial.vista.casino

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        palabra = findViewById(R.id.palabro)
        gridLetras = findViewById(R.id.gridLetras)
        listaNegra = findViewById(R.id.listaNegra)
        this.juego = Ahorcado(this.getApuesta())
        this.juego.addPropertyChangeListener(this)
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
            b?.text = i.toString()
            b?.setOnClickListener{
                ejecutar(i.toString())
            }
            gridLetras.addView(b)
        }
    }
    override fun propertyChange(p0: PropertyChangeEvent?) {
        if(p0?.propertyName.equals("Letra acertada")){
            palabra.text = (p0?.oldValue).toString().replace("]","").replace("[","").replace(",","")
        }
        else if(p0?.propertyName.equals("Letra fallada")){
            listaNegra.text = (p0?.oldValue).toString().replace("]","").replace("[","")
        }
        else if(p0?.propertyName.equals("Primera peticion")){
            palabra.text = (p0?.oldValue).toString().replace("]","").replace("[","").replace(",","")
        }
        else super.propertyChange(p0)
    }
}