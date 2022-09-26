package com.example.terrivial.modelo;

import android.content.Context;

import com.example.terrivial.vista.MainActivity;

import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

public class Partida {
    private Pregunta preguntaActual;
    private static Partida mPartida;
    private int puntos;
    private boolean finalizada;
    private List<Categoria> categorias;
    private Categoria categoriaActual;
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
    public void addListeners(PropertyChangeListener l){
        this.categorias.forEach(c -> c.addPropertyChangeListener(l));
    }
    public boolean esRespuestaCorrecta(String r){
        if(preguntaActual.respuestaCorrecta(r)){
            categoriaActual.asignarPunticos(preguntaActual.getSubCateg(),true);
            return true;
        }
       else {
            categoriaActual.asignarPunticos("",false);
            return false;
        }
    }

    public void llenarMapas(Context cc){
       categorias.forEach(c -> c.llenarMapa(cc));
    }
    public void actualizarPregunta(String c){
        this.categoriaActual = categorias.stream().filter(cc -> cc.getNombre().equalsIgnoreCase(c)).findAny().get();
       this.preguntaActual = categoriaActual.preguntaRandom();
    }
    public Pregunta getPreguntaActual(){
        return this.preguntaActual;
    }
    public void anadirPuntos(int p){
        this.puntos += p;
        if(p==5) finalizada = true;
    }
}
