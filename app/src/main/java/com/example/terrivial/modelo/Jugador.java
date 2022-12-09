package com.example.terrivial.modelo;

import java.util.Arrays;

public class Jugador {
    private int monedas;
    private final Potenciador[] potenciadores;
    private static Jugador mJugador;
    private Jugador(){
        monedas = 100;
        potenciadores = Potenciador.values();
    }

    public int getMonedas() {
        return monedas;
    }

    public Potenciador[] getPotenciadores() {
        return potenciadores;
    }
    public void restarMonedas(int monedos){
        this.monedas-=monedos;
    }
    public static Jugador getInstance(){
        if(mJugador == null) mJugador = new Jugador();
        return mJugador;
    }
    public Potenciador getPotenciador(String pot){
        return Arrays.stream(potenciadores).filter(i -> i.getNombre().equals(pot)).findFirst().orElse(null);
    }
}
