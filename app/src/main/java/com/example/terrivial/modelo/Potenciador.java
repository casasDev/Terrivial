package com.example.terrivial.modelo;

import com.example.terrivial.vista.FiftyFIfty;
import com.example.terrivial.vista.PasarPregunta;
import com.example.terrivial.vista.RespuestaCorrecta;
import com.example.terrivial.vista.Strategy;

public enum Potenciador {
    FIFTYFIFTY("FiftyFifty",100, new FiftyFIfty()), RESPUESTACORRECTA("RespuestaCorrecta",100, new RespuestaCorrecta()), PASARPREGUNTA("PasarPregunta",100, new PasarPregunta()) ;
    private int coste;
    private int cantidad;
    private String nombre;
    private Strategy s;
    Potenciador(String pNombre, int pCoste, Strategy s){
        this.cantidad = 0;
        this.nombre = pNombre;
        this.coste = pCoste;
        this.s = s;
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
}
