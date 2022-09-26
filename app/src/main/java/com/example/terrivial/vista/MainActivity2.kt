package com.example.terrivial.vista

import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.gridlayout.widget.GridLayout
import android.widget.RadioButton
import android.widget.Toast
import androidx.annotation.IdRes
import androidx.core.view.children
import com.example.terrivial.R
import com.example.terrivial.modelo.Ciencia
import com.example.terrivial.modelo.Entretenimiento
import com.example.terrivial.modelo.Geopolitica
import com.example.terrivial.modelo.Partida
import java.beans.PropertyChangeEvent
import java.beans.PropertyChangeListener
import java.util.*
import kotlin.collections.HashMap

class MainActivity2 : AppCompatActivity(), PropertyChangeListener{
    private lateinit var grids : List<GridLayout>
    private val punticos : MutableMap<String, RadioButton> = HashMap()
    private lateinit var botones : List<Button>
    private val partida = Partida.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        partida.addListeners(this)
        grids = listOf(findViewById(R.id.geop),findViewById(R.id.cienc),findViewById(R.id.entr))
        generarRadio()
        botones = listOf(findViewById(R.id.geo),findViewById(R.id.ceincia),findViewById(R.id.entretenimiento))
        botonesCategoria()
    }
    private fun generarRadio() {
        grids.forEach { g ->
                when (g.id) {
                    R.id.geop -> Geopolitica.getInstance().pregunticas.keys.forEach { sc ->
                        val r = RadioButton(this)
                        r.buttonTintList = ColorStateList.valueOf(Color.BLUE)
                        r.setOnClickListener{
                            if (r.isChecked && !Geopolitica.getInstance().punticos[sc]!!) r.isChecked = false
                        }
                        r.setOnLongClickListener{
                            Toast.makeText(this, sc, Toast.LENGTH_SHORT).show()
                            false
                        }
                        g.addView(r)
                        punticos.put(sc, r)
                    }
                    R.id.cienc -> Ciencia.getInstance().pregunticas.keys.forEach{ sc ->
                        val r = RadioButton(this)
                        r.buttonTintList = ColorStateList.valueOf(Color.GREEN)
                        r.setOnClickListener{
                            if (r.isChecked && !Ciencia.getInstance().punticos[sc]!!) r.isChecked = false
                        }
                        r.setOnLongClickListener{
                            Toast.makeText(this, sc, Toast.LENGTH_SHORT).show()
                            false
                        }
                        g.addView(r)
                        punticos.put(sc, r)
                    }
                    R.id.entr -> Entretenimiento.getInstance().pregunticas.keys.forEach{ sc ->
                        val r = RadioButton(this)
                        r.buttonTintList = ColorStateList.valueOf(Color.MAGENTA)
                        r.setOnClickListener{
                            if (r.isChecked && !Entretenimiento.getInstance().punticos[sc]!!) r.isChecked = false
                        }
                        r.setOnLongClickListener{
                            Toast.makeText(this, sc, Toast.LENGTH_SHORT).show()
                          false
                        }
                        g.addView(r)
                        punticos.put(sc, r)
                    }
                }
            }
        }
    private fun botonesCategoria(){
        botones.forEach{ b ->
            b.setOnClickListener{
                partida.actualizarPregunta(b.text.toString())
                startActivity(Intent(this, Respondeme::class.java))
            }
        }
    }

    override fun propertyChange(p0: PropertyChangeEvent?) {
        if(p0?.propertyName.equals("subPuntico"))
            punticos[p0!!.oldValue]!!.isChecked = true
       else if(p0?.propertyName.equals("fallaste")){
            (p0!!.oldValue as Set<*>).forEach{
                punticos[it]!!.isChecked = false
            }
        }
    }
}