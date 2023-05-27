package com.example.terrivial.vista.casino

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.terrivial.R
import com.example.terrivial.vista.MainActivity

class Casino : AppCompatActivity() {
    private lateinit var recyclerView : RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_casino)
        recyclerView = findViewById(R.id.jueguitos)
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerView.adapter = AdapterRecyclerJuegos()
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