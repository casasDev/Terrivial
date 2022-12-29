package com.example.terrivial.vista

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import com.example.terrivial.R
import com.example.terrivial.modelo.Partida
import com.example.terrivial.modelo.Potenciador

class AdaptadorRecyclerPotenciadores : RecyclerView.Adapter<AdaptadorRecyclerPotenciadores.ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var cantidad : TextView
        var foto : ImageView

        init {
            cantidad = itemView.findViewById(R.id.canti)
            foto = itemView.findViewById(R.id.fotopoten)
            itemView.setOnClickListener{
                Partida.getInstance().hacerAccion(foto.contentDescription.toString())
                YoYo.with(Techniques.FadeOut).duration(250).playOn(it)
                it.isEnabled = false
            }
        }
    }
    override fun onCreateViewHolder(parent : ViewGroup, viewType : Int) : ViewHolder{
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.boton_poten, parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.cantidad.text = Potenciador.values()[position].cantidad.toString()
        holder.foto.setImageResource(Potenciador.values()[position].foto)
        holder.foto.contentDescription = Potenciador.values()[position].nombre
    }

    override fun getItemCount(): Int {
        return Potenciador.values().size
    }
}