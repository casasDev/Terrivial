package com.example.terrivial.vista.casino

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.RelativeLayout
import androidx.core.view.children
import androidx.gridlayout.widget.GridLayout
import com.example.terrivial.R
import com.example.terrivial.modelo.casino.battleship.Battleship
import com.example.terrivial.modelo.casino.battleship.Casilla
import com.example.terrivial.modelo.casino.battleship.JugadorBattleship
import com.example.terrivial.modelo.casino.battleship.TipoEstado
import java.beans.PropertyChangeEvent
import java.util.stream.IntStream.range
class BattleshipGUI : JuegoView() {
    private lateinit var oponente : GridLayout
    private lateinit var aliado : GridLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        oponente = findViewById(R.id.openente)
        aliado = findViewById(R.id.aliado)
        oponente.columnCount = 10
        oponente.rowCount = 10
        aliado.columnCount = 10
        aliado.rowCount = 10
        generarBotones(oponente)
        generarBotones(aliado)
        this.juego = Battleship(this.getApuesta())
        this.juego.addPropertyChangeListener(this)
    }

    override fun getLayoutID(): Int {
        return R.layout.activity_battleship_gui
    }

    fun generarBotones(tablero : GridLayout){
        range(0, tablero.rowCount).forEach {
            range(0,tablero.columnCount).forEach { j->
                val b = Button(this)
                b.contentDescription = "$it,$j"
                b.layoutParams = RelativeLayout.LayoutParams(60,60)
                b.setOnClickListener{
                    juego.ejecutar(it.contentDescription.toString())
                }
                if(tablero == this.aliado) b.isEnabled = false
                tablero.addView(b)
            }
        }
    }

    override fun propertyChange(p0: PropertyChangeEvent?) {
        if(p0?.propertyName.equals("Barco")){
            val casilla = p0!!.oldValue as Casilla
            aliado.getChildAt(casilla.fila * aliado.columnCount + casilla.columna).background.setTint(Color.GREEN)
        }
        else if(p0?.propertyName.equals("Tocado")){
            val casilla = p0!!.oldValue as Casilla
            var tablero: GridLayout? = null
            if(p0.newValue is JugadorBattleship) tablero = oponente
            else tablero = aliado
            tablero.getChildAt(casilla.fila * aliado.columnCount + casilla.columna).background.setTint(Color.RED)
            tablero.getChildAt(casilla.fila * aliado.columnCount + casilla.columna).isEnabled = false
        }
        else if(p0?.propertyName.equals("Hundido")){
            val casillas = p0!!.oldValue as List<Casilla>
            var tablero: GridLayout? = null
            if(p0.newValue is JugadorBattleship) tablero = oponente
            else tablero = aliado
            casillas.forEach{
                tablero!!.getChildAt(it.fila*aliado.columnCount+it.columna).background.setTint(Color.BLACK)
            }
        }
        else if(p0?.propertyName.equals("Agua")){
            val casilla = p0!!.oldValue as Casilla
            var tablero: GridLayout? = null
            if(p0.newValue is JugadorBattleship) tablero = oponente
            else tablero = aliado
            tablero.getChildAt(casilla.fila * aliado.columnCount + casilla.columna).background.setTint(Color.BLUE)
            tablero.getChildAt(casilla.fila * aliado.columnCount + casilla.columna).isEnabled = false
        }
        else super.propertyChange(p0)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        oponente.children.forEach { it.background.setTint(Color.GRAY) }
        aliado.children.forEach { it.background.setTint(Color.GRAY) }

    }
}