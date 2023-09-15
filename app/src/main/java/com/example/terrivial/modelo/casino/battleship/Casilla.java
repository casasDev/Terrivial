package com.example.terrivial.modelo.casino.battleship;

public class Casilla {
    private final int fila;
    private final int columna;
    private TipoEstado estado;
    public Casilla(int fila, int columna){
        this.fila = fila;
        this.columna = columna;
    }

    public int getColumna() {
        return columna;
    }

    public TipoEstado getEstado() {
        return estado;
    }

    public int getFila() {
        return fila;
    }

    public void setEstado(TipoEstado estado) {
        this.estado = estado;
    }
}
