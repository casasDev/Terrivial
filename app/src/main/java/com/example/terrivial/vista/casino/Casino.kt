package com.example.terrivial.vista.casino

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.terrivial.R
import com.example.terrivial.modelo.casino.AdivinarNumero
import com.example.terrivial.vista.MainActivity
import org.w3c.dom.Text

class Casino : AppCompatActivity() {
    private lateinit var b : Button
    private lateinit var b2 : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_casino)
        b = findViewById(R.id.button)
        b2 = findViewById(R.id.button22)
        b.setOnClickListener { startActivity(Intent(this,AdivinaNumero::class.java)) }
        b2.setOnClickListener { startActivity(Intent(this,Ahorcadito::class.java)) }
    }
    override fun onPause() {
        super.onPause()
        if(MainActivity.mp.isPlaying)
            MainActivity.mp.pause()
    }

    override fun onResume() {
        super.onResume()
        if(!MainActivity.mp.isPlaying && !MainActivity.mute.isChecked)
            MainActivity.mp.start()
    }
}