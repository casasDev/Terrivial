package com.example.terrivial.vista

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.terrivial.R
import com.example.terrivial.modelo.Potenciador

class AdapterRecyclerTienda : RecyclerView.Adapter<AdapterRecyclerTienda.ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var precio : TextView
        var descr : TextView
        var comprar : Button
        var foto : ImageView
        init {
            precio = itemView.findViewById(R.id.precio)
            descr = itemView.findViewById(R.id.descr)
            comprar = itemView.findViewById(R.id.comprar)
            foto = itemView.findViewById(R.id.fotoTienda)
        }


    }
    private var potenciadores : Array<Potenciador> = Potenciador.values()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.tienda_cards, parent,false))
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.descr.text = holder.itemView.context.getString(potenciadores[position].descripcion)
        holder.foto.setImageResource(potenciadores[position].foto)
        holder.precio.text = potenciadores[position].coste.toString() + " monedas"
    }
    override fun getItemCount(): Int {
        return potenciadores.size
    }



}
