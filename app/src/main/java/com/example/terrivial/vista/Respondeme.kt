package com.example.terrivial.vista

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.terrivial.R
import com.example.terrivial.modelo.Partida

class Respondeme : AppCompatActivity() {
    private lateinit var tv : TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)
        tv = findViewById(R.id.textView)
        tv.text = Partida.getInstance().preguntaActual.toString()

    }
}