package com.example.terrivial.vista
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.terrivial.R
import com.example.terrivial.modelo.Partida

class MainActivity : AppCompatActivity() {
    private val partida = Partida.getInstance()
    private lateinit var jugar: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        partida.llenarMapas(this)
        jugar = findViewById(R.id.jugar)
        jugar.setOnClickListener {

            startActivity(Intent(this, MainActivity2::class.java))
        }
    }



}