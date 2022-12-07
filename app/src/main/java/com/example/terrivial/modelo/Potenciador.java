package com.example.terrivial.modelo;

public enum Potenciador {
    FIFTYFIFTY("FiftyFifty",100), RESPUESTACORRECTA("RespuestaCorrecta",100), PASARPREGUNTA("PasarPregunta",100);
    private int coste;
    private int cantidad;
    private String nombre;

    Potenciador(String pNombre, int pCoste){
        this.cantidad = 0;
        this.nombre = pNombre;
        this.coste = pCoste;
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
}
