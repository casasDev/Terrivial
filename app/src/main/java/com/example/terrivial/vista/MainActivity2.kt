package com.example.terrivial.vista

import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.RadioButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.gridlayout.widget.GridLayout
import com.example.terrivial.R
import com.example.terrivial.modelo.*
import java.beans.PropertyChangeEvent
import java.beans.PropertyChangeListener

class MainActivity2 : AppCompatActivity(), PropertyChangeListener{
    private lateinit var grids : List<GridLayout>
    private val punticos : MutableMap<String, RadioButton> = HashMap()
    private lateinit var botones : List<Button>
    private val partida = Partida.getInstance()
    private lateinit var puntos : TextView
    private lateinit var scaleUp : Animation
    private lateinit var scaleDown : Animation
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        this.puntos = findViewById(R.id.puntos)
        scaleUp = AnimationUtils.loadAnimation(this,R.anim.scale_up1)
        scaleDown = AnimationUtils.loadAnimation(this, R.anim.scale_down1)
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
                        punticos[sc] = r
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
                        punticos[sc] = r
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
                        punticos[sc] = r
                    }
                }
            }
        }
    @SuppressLint("ClickableViewAccessibility")
    private fun botonesCategoria(){
        botones.forEach{ b ->
            b.setOnClickListener {
                partida.actualizarPregunta(b.text.toString())
                startActivity(Intent(this, Respondeme::class.java))
            }
            b.setOnTouchListener{
                    _: View, motion : MotionEvent ->
                if(motion.action == MotionEvent.ACTION_DOWN)
                    b.startAnimation(scaleUp)
                else if (motion.action == MotionEvent.ACTION_UP)
                    b.startAnimation(scaleDown)
                false
            }
        }
    }

    override fun propertyChange(p0: PropertyChangeEvent?) {
        if (p0?.propertyName.equals("fin")){
            this.punticos.values.forEach{
                it.visibility = View.GONE
            }
            this.botones.forEach{
                it.visibility = View.GONE
            }
            this.puntos.text = getString(R.string.fin)
        }
        else if(p0?.propertyName.equals("subPuntico")) {
            punticos[p0!!.oldValue]!!.isChecked = true
        } else if(p0?.propertyName.equals("fallaste")){
            (p0!!.oldValue as Set<*>).forEach{
                punticos[it]!!.isChecked = false
            }
        } else if(p0?.propertyName.equals("puntoConseguido")){
            botones.filter{
                it.text.toString().equals(p0!!.oldValue.toString(), true)
            }[0].isEnabled = false
            (p0!!.newValue as Categoria).pregunticas.keys.forEach {
                punticos[it]!!.visibility = View.GONE
            }
        } else if(p0?.propertyName.equals("puntacos")) this.puntos.text = p0!!.oldValue as String

    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}
