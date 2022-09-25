package com.example.terrivial.modelo;

import android.content.Context;

import com.example.terrivial.vista.MainActivity;

import java.util.ArrayList;
import java.util.List;

public class Partida {
    private Pregunta preguntaActual;
    private static Partida mPartida;
    private int puntos;
    private boolean finalizada;
    private List<Categoria> categorias;
    private Partida(){
        finalizada = false;
        puntos = 0;
        categorias = new ArrayList<>();
        categorias.add(Entretenimiento.getInstance());
        categorias.add(Ciencia.getInstance());
        categorias.add(Geopolitica.getInstance());
    }
    public static Partida getInstance(){
        if(mPartida == null) mPartida = new Partida();
        return mPartida;
    }

    public void llenarMapas(Context cc){
       categorias.forEach(c -> c.llenarMapa(cc));
    }
    public void actualizarPregunta(String c){
       this.preguntaActual = categorias.stream().filter(cc -> cc.getNombre().equalsIgnoreCase(c)).findAny().get().preguntaRandom();
    }
    public Pregunta getPreguntaActual(){
        return this.preguntaActual;
    }
    public void anadirPuntos(int p){
        this.puntos += p;
        if(p==5) finalizada = true;
    }
}
