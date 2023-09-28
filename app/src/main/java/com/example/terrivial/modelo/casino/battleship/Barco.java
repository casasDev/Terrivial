package com.example.terrivial.modelo.casino.battleship;

import java.util.ArrayList;
import java.util.List;

public class Barco {
    private final List<Casilla> listaCasillas;
    private final int tamanio;
    private final boolean horizontal;
    public Barco(int tamanio, boolean horizontal){
        this.horizontal = horizontal;
        this.tamanio = tamanio;
        listaCasillas = new ArrayList<>();
    }

    public boolean isHorizontal() {
        return horizontal;
    }

    public int getTamanio() {
        return tamanio;
    }
    public void anadirCasilla(Casilla casilla){
       this.listaCasillas.add(casilla);
    }
    public boolean estaHundido(){
        return this.listaCasillas.stream().allMatch(c -> c.getEstado()==TipoEstado.TOCADO||c.getEstado()==TipoEstado.HUNDIDO);
    }
    public void hundir(){
        this.listaCasillas.forEach(c -> c.setEstado(TipoEstado.HUNDIDO));
    }

    public List<Casilla> getListaCasillas() {
        return this.listaCasillas;
    }
}
