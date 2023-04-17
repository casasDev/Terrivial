package com.example.terrivial.modelo.casino;

import com.example.terrivial.modelo.Partida;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public abstract class Minijuego{
    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
    private final int apuesta,foto;
    private final String nombre, descripcion;
    public Minijuego(int apuesta, int foto, String descripcion, String nombre){
        this.apuesta = apuesta;
        this.foto = foto;
        this.descripcion = descripcion;
        this.nombre = nombre;
    }
    public void addPropertyChangeListener(PropertyChangeListener p){
        this.pcs.addPropertyChangeListener(p);
    }
    protected PropertyChangeSupport getPcs(){
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
    protected void apuestaAcabada(boolean lograda){
        if(lograda) {
            Partida.getInstance().setMonedas(Partida.getInstance().getMonedas() + (this.apuesta * 2));
            pcs.firePropertyChange("Apuesta lograda", null, null);
        } else pcs.firePropertyChange("Perdiste",getRespuesta(),null);
    }
    public abstract void ejecutar(String s);
    protected abstract String getRespuesta();
}
