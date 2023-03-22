package com.example.terrivial.vista

import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.terrivial.R
import com.example.terrivial.modelo.Partida
import com.example.terrivial.modelo.Potenciador
import java.beans.PropertyChangeEvent
import java.beans.PropertyChangeListener

class AdapterRecyclerTienda : RecyclerView.Adapter<AdapterRecyclerTienda.ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        PropertyChangeListener {
        var precio: TextView
        var descr: TextView
        var comprar: Button
        var foto: ImageView
        var tienes: TextView
        var scaleUp: Animation
        var scaleDown: Animation

        init {
            precio = itemView.findViewById(R.id.precio)
            descr = itemView.findViewById(R.id.descr)
            comprar = itemView.findViewById(R.id.comprar)
            tienes = itemView.findViewById(R.id.tienes)
            foto = itemView.findViewById(R.id.fotoTienda)
            scaleUp = AnimationUtils.loadAnimation(itemView.context, R.anim.scale_up2)
            scaleDown = AnimationUtils.loadAnimation(itemView.context, R.anim.scale_down2)
        }

        override fun propertyChange(p0: PropertyChangeEvent?) {
            if (p0!!.propertyName.equals("Canti")) {
                tienes.text = "TIENES: " + (p0.oldValue as Int)
            }
        }


    }

    private var potenciadores: Array<Potenciador> = Potenciador.values()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.tienda_cards, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        potenciadores[position].addPropertyChangeListener(holder)
        potenciadores[position].cantidad =
            holder.itemView.context.getSharedPreferences("DatosPalyer", 0)
                .getInt("Cantidades" + potenciadores[position].nombre, 0)
        holder.descr.text = holder.itemView.context.getString(potenciadores[position].descripcion)
        holder.foto.setImageResource(potenciadores[position].foto)
        holder.tienes.text = "TIENES: " + potenciadores[position].cantidad
        holder.precio.text = potenciadores[position].coste.toString() + " monedas"
        holder.comprar.setOnClickListener {
            if (potenciadores[position].tienesMonedos()) {
                Partida.getInstance().monedas =
                    Partida.getInstance().monedas - potenciadores[position].coste
                potenciadores[position].incrementarCantidad()
                val editor = it.context.getSharedPreferences("DatosPalyer", 0).edit()
                editor.putInt(
                    "Cantidades" + potenciadores[position].nombre,
                    potenciadores[position].cantidad
                )
                editor.commit()
                editor.apply()
                GestorSonidos.queSuene(holder.itemView.context, R.raw.chiclin)
            } else
                Toast.makeText(
                    it.context,
                    "Pero tu eres bobo, que no tienes dineros",
                    Toast.LENGTH_LONG
                ).show()
        }
        holder.comprar.setOnTouchListener { view: View, motionEvent: MotionEvent ->
            if (motionEvent.action == MotionEvent.ACTION_DOWN)
                view.startAnimation(holder.scaleDown)
            else view.startAnimation(holder.scaleUp)
            false
        }
    }

    override fun getItemCount(): Int {
        return potenciadores.size
    }


}
