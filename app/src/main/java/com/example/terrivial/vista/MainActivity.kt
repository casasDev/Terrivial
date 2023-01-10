package com.example.terrivial.vista
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.terrivial.R
import com.example.terrivial.modelo.Partida

class MainActivity : AppCompatActivity() {
    private val partida = Partida.getInstance()
    private lateinit var jugar: Button
    private lateinit var scaleUp : Animation
    private lateinit var scaleDown : Animation
    private lateinit var tienda : Button
    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        partida.llenarMapas(this)
        jugar = findViewById(R.id.jugar)
        tienda = findViewById(R.id.tienda)
        scaleUp = AnimationUtils.loadAnimation(this, R.anim.scale_up1)
        scaleDown = AnimationUtils.loadAnimation(this, R.anim.scale_down1)
        jugar.setOnClickListener {
            startActivity(Intent(this, MainActivity2::class.java))
        }
        tienda.setOnClickListener{
            startActivity(Intent(this,Tienda::class.java))
        }
        jugar.setOnTouchListener{ view:View, motionEvent: MotionEvent ->
            onTouch(view,motionEvent)
        }
        tienda.setOnTouchListener{
                view:View, motionEvent: MotionEvent ->
            onTouch(view,motionEvent)
        }

    }
    private fun onTouch(view: View,motionEvent: MotionEvent):Boolean{
        if(motionEvent.action == MotionEvent.ACTION_DOWN){
            view.startAnimation(scaleUp)
        }
        else if(motionEvent.action == MotionEvent.ACTION_UP)
            view.startAnimation(scaleDown)
        return false
    }


}