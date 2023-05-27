package com.example.terrivial.vista.casino

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.terrivial.R
import com.example.terrivial.modelo.Partida
import com.example.terrivial.modelo.casino.Minijuego
import com.example.terrivial.modelo.casino.Minijuegos

class AdapterRecyclerJuegos : RecyclerView.Adapter<AdapterRecyclerJuegos.ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var foto: ImageView
        var titulo: TextView
        var descr: TextView
        var apuesta: EditText
        var jugar: Button

        init {
            foto = itemView.findViewById(R.id.fotoJuego)
            titulo = itemView.findViewById(R.id.tituloJuego)
            descr = itemView.findViewById(R.id.descripcion_juego)
            apuesta = itemView.findViewById(R.id.apuesta)
            jugar = itemView.findViewById(R.id.iniciar_juego)
        }
    }

    var miniJ: Array<Minijuegos> = Minijuegos.values()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.juegos_cards, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return miniJ.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.foto.setImageResource(miniJ[position].foto)
        holder.titulo.text = miniJ[position].nombre
        holder.descr.text = miniJ[position].descripcion
        holder.jugar.setOnClickListener { e ->
            if (holder.apuesta.text.toString() != "" && Integer.parseInt(holder.apuesta.text.toString()) > 0 && Integer.parseInt(
                    holder.apuesta.text.toString()
                ) <= Partida.getInstance().monedas
            ) {
                Minijuego.setApuestaActual(Integer.parseInt(holder.apuesta.text.toString()))
                holder.apuesta.text.clear()
                holder.itemView.context.startActivity(JuegoFactory.generate(miniJ[position].nombre,holder.itemView.context))
            }
            else Toast.makeText(
                holder.itemView.context,
                "Sí claro, tú quieres ir al casino sin apostar o apostando más de lo que tienes; ponme algo que tenga sentido mascachapas",
                Toast.LENGTH_LONG
            ).show()
        }
    }
}