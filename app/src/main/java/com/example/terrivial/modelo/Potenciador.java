package com.example.terrivial.modelo;

import com.example.terrivial.R;
import com.example.terrivial.vista.FiftyFIfty;
import com.example.terrivial.vista.PasarPregunta;
import com.example.terrivial.vista.RespuestaCorrecta;
import com.example.terrivial.vista.Strategy;

public enum Potenciador {
    FIFTYFIFTY("FiftyFifty",100, new FiftyFIfty(), R.drawable.fiftyfifty, R.string.descr_ff), RESPUESTACORRECTA("RespuestaCorrecta",500, new RespuestaCorrecta(), R.drawable.respuestacorrecta2,R.string.descr_respcor), PASARPREGUNTA("PasarPregunta",150, new PasarPregunta(), R.drawable.pasar,R.string.descr_pasar) ;
    private final int coste;
    private int cantidad;
    private final String nombre;
    private final Strategy s;
    private final int foto;
    private final int descripcion;
    Potenciador(String pNombre, int pCoste, Strategy s, int foto, int descripcion){
        this.foto = foto;
        this.cantidad = 0;
        this.nombre = pNombre;
        this.coste = pCoste;
        this.s = s;
        this.descripcion = descripcion;
    }
    public int getCoste() {
        return coste;
    }

    public int getCantidad() {
        return cantidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    public void incrementarCantidad(){
        this.cantidad++;
    }
    public void decrementarCantidad(){
        this.cantidad--;
    }

    public Strategy getStrategy() {
        return s;
    }

    public int getFoto() {
        return foto;
    }
    public int getDescripcion(){
        return this.descripcion;
    }
}
