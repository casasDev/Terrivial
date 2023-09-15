package com.example.terrivial.modelo.casino.battleship;

import java.util.ArrayList;
import java.util.List;

public abstract class Usuario {
    private Tablero tableroPropio;
    private Tablero tableroRival;
    private final List<Barco> listaBarcos;
    protected Usuario(){
        listaBarcos = new ArrayList<>();
    }
    protected abstract void disparar(int x, int y);
    private void generarYColocarBarcos(){}
}
