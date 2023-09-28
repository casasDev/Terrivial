package com.example.terrivial.modelo.casino.battleship;

import static java.util.Arrays.stream;

import com.example.terrivial.modelo.casino.Minijuego;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class Battleship extends Minijuego implements PropertyChangeListener {
    private final Usuario[] jugadores = {new JugadorBattleship(), new Computador()};

    public Battleship(int apuesta) {
        super(apuesta, 0, "Si ganas se te duplican las monedas. Clásico hundir la flota no tiene misterio", "Hundir la flota");
        stream(jugadores).forEach(j -> j.addPCL(this));
        jugadores[0].setTableroRival(jugadores[1].getTableroPropio());
        jugadores[1].setTableroRival(jugadores[0].getTableroPropio());
        ((Computador) jugadores[1]).anadirCasillaPosibles();
    }

    @Override
    public void ejecutar(String s) {
        String[] s2 = s.split(",");
        jugadores[0].disparar(Integer.parseInt(s2[0]), Integer.parseInt(s2[1]),jugadores[1].getListaBarcos());
        jugadores[1].disparar(0, 0,jugadores[0].getListaBarcos());
    }

    @Override
    protected String getRespuesta() {
        return "";
    }

    public Usuario[] getJugadores() {
        return jugadores;
    }

    @Override
    public void propertyChange(PropertyChangeEvent propertyChangeEvent) {
        if (!propertyChangeEvent.getPropertyName().equals("Derrota"))
            this.getPcs().firePropertyChange(propertyChangeEvent.getPropertyName(), propertyChangeEvent.getOldValue(), propertyChangeEvent.getNewValue());
        else apuestaAcabada(!(propertyChangeEvent.getOldValue() instanceof JugadorBattleship));
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener pcl) {
        super.addPropertyChangeListener(pcl);
        stream(jugadores).forEach(j -> j.generarYColocarBarcos(4));
    }
}
