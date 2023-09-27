package com.example.terrivial.modelo.casino.battleship;

import static java.util.Arrays.stream;

import com.example.terrivial.modelo.casino.Minijuego;

import java.beans.PropertyChangeListener;

public class Battleship extends Minijuego {
    private final Usuario[] jugadores = {new Jugador(), new Computador()};

    public Battleship(int apuesta) {
        super(apuesta, 0, "Si ganas se te duplican las monedas. Clásico hundir la flota no tiene misterio", "Hundir la flota");
    }

    @Override
    public void ejecutar(String s) {
    //TO-DO
    }

    public void addListeners(PropertyChangeListener pcl) {
        stream(jugadores).forEach(j -> {
            j.addPCL(pcl);
            j.generarYColocarBarcos(4);
        });
    }

    @Override
    protected String getRespuesta() {
        return "";
    }

    public Usuario[] getJugadores() {
        return jugadores;
    }
}
