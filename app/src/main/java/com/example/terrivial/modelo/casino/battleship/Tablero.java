package com.example.terrivial.modelo.casino.battleship;
import static java.util.stream.IntStream.range;
public class Tablero {
    private static final int COLUMNAS = 10;
    private static final int FILAS = 10;
    private final Casilla[][] casillas;

    public Tablero() {
        this.casillas = new Casilla[FILAS][COLUMNAS];
        range(0,FILAS).forEach(i-> range(0,COLUMNAS).forEach(j->{
            casillas[i][j] = new Casilla(i,j);
            getCasilla(i,j).setEstado(TipoEstado.NADA);
        }));
    }
    public Casilla getCasilla(int x, int y){
        return this.casillas[x][y];
    }
    public static int getColumnas(){
        return COLUMNAS;
    }
    public static int getFilas(){
        return FILAS;
    }

    public Casilla[][] getCasillas() {
        return this.casillas;
    }
}
