package com.example.terrivial.modelo;

import android.content.Context;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

public class Partida {
    private Pregunta preguntaActual;
    private static Partida mPartida;
    private int puntos;
    private boolean finalizada;
    private final List<Categoria> categorias;
    private Categoria categoriaActual;
    private final PropertyChangeSupport p = new PropertyChangeSupport(this);
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
    public boolean isFinalizada(){
        return this.finalizada;
    }
    public void addPropertyChangeListener(PropertyChangeListener l){
        this.p.addPropertyChangeListener(l);
    }
    public void addListeners(PropertyChangeListener l){
        this.categorias.forEach(c -> c.addPropertyChangeListener(l));
        this.addPropertyChangeListener(l);
        this.anadirPuntos(0);
    }
    public boolean esRespuestaCorrecta(String r){
        if(preguntaActual.respuestaCorrecta(r)){
            categoriaActual.asignarPunticos(preguntaActual.getSubCateg(),true);
            if(isFinalizada()) this.p.firePropertyChange("fin",null,null);
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
        this.categoriaActual = categorias.stream().filter(cc -> cc.getNombre().equalsIgnoreCase(c)).findAny().orElse(null);
       if(categoriaActual !=null)
        this.preguntaActual = categoriaActual.preguntaRandom();
    }
    public Pregunta getPreguntaActual(){
        return this.preguntaActual;
    }
    public Categoria getCategoriaActual(){
        return this.categoriaActual;
    }
    public void anadirPuntos(int p){
        this.puntos += p;
        this.p.firePropertyChange("puntacos", "PUNTOS: "+this.puntos,false);
        if(this.puntos==this.categorias.stream().mapToInt(c -> c.getPregunticas().keySet().size()).sum()) {
            finalizada = true;
            this.puntos =0;
        }

    }
    public Categoria getCategoria(String pCateg){
        return categorias.stream().filter(c -> c.getNombre().equalsIgnoreCase(pCateg)).findAny().orElse(null);
    }
}
