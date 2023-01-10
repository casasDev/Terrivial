package com.example.terrivial.vista

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.terrivial.R
import com.example.terrivial.modelo.Potenciador

class Tienda : AppCompatActivity() {
    private lateinit var recyclerTienda : RecyclerView
    private lateinit var cardsPotenciador : AdapterRecyclerTienda
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.l_tienda)
        recyclerTienda = findViewById(R.id.recyclerTienda)
        recyclerTienda.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        cardsPotenciador = AdapterRecyclerTienda()
        recyclerTienda.adapter = cardsPotenciador
    }
}