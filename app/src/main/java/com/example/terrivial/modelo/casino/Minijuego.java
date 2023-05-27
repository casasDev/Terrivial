package com.example.terrivial.modelo.casino;

import com.example.terrivial.modelo.Partida;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public abstract class Minijuego {
    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
    private final int apuesta, foto;
    private final String nombre, descripcion;
    private static int apuestaActual;

    public Minijuego(int apuesta, int foto, String descripcion, String nombre) {
        this.apuesta = apuesta;
        this.foto = foto;
        this.descripcion = descripcion;
        this.nombre = nombre;
    }

    public void addPropertyChangeListener(PropertyChangeListener p) {
        this.pcs.addPropertyChangeListener(p);
    }

    protected PropertyChangeSupport getPcs() {
        return this.pcs;
    }

    public int getApuesta() {
        return this.apuesta;
    }

    public int getFoto() {
        return this.foto;
    }

    public String getNombre() {
        return this.nombre;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public static void setApuestaActual(int apuesta) {
        apuestaActual = apuesta;
    }

    public static int getApuestaActual() {
        return apuestaActual;
    }

    protected void apuestaAcabada(boolean lograda) {
        if (lograda) {
            Partida.getInstance().setMonedas(Minijuego.getApuestaActual() * 2 + Partida.getInstance().getMonedas());
            pcs.firePropertyChange("Apuesta lograda", null, null);
        } else {
            Partida.getInstance().setMonedas(Partida.getInstance().getMonedas()-Minijuego.getApuestaActual());
            pcs.firePropertyChange("Perdiste", getRespuesta(), null);
        }
    }

    public abstract void ejecutar(String s);

    protected abstract String getRespuesta();
}
