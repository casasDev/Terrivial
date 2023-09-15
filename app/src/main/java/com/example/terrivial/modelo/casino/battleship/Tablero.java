package com.example.terrivial.modelo.casino.battleship;

public class Tablero {
    private final int COLUMNAS = 10;
    private final int FILAS = 10;
    private final Casilla[][] casillas;

    public Tablero() {
        this.casillas = new Casilla[FILAS][COLUMNAS];
    }
    public Casilla getCasilla(int x, int y){
        return this.casillas[x][y];
    }
}
