package com.example.terrivial.modelo.casino.battleship;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static java.util.stream.IntStream.range;
public class Computador extends Usuario{
    private final List<Casilla> casillasPosibles;
    public Computador(){
        super();
        casillasPosibles = new ArrayList<>();
    }
    @Override
    protected void disparar(int x, int y,List<Barco> barcoList) {
        Casilla c = casillasPosibles.get(new Random().nextInt(casillasPosibles.size()));
        super.disparar(c.getFila(),c.getColumna(),barcoList);
        casillasPosibles.remove(c);
    }
    public void anadirCasillaPosibles(){
        range(0,Tablero.getFilas()).forEach(i -> range(0,Tablero.getColumnas()).forEach(j -> casillasPosibles.add(this.getTableroRival().getCasilla(i,j))));
    }
}
