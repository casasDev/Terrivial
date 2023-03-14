package com.example.terrivial.modelo;

import com.example.terrivial.R;
import com.example.terrivial.vista.FiftyFIfty;
import com.example.terrivial.vista.PasarPregunta;
import com.example.terrivial.vista.RespuestaCorrecta;
import com.example.terrivial.vista.Strategy;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public enum Potenciador{
    FIFTYFIFTY("FiftyFifty",100, new FiftyFIfty(), R.drawable.fiftyfifty, R.string.descr_ff,R.raw.bomba), RESPUESTACORRECTA("RespuestaCorrecta",500, new RespuestaCorrecta(), R.drawable.respuestacorrecta2,R.string.descr_respcor,R.raw.acertar), PASARPREGUNTA("PasarPregunta",150, new PasarPregunta(), R.drawable.pasar,R.string.descr_pasar,R.raw.slidein) ;
    private final int coste;
    private int cantidad;
    private final String nombre;
    private final Strategy s;
    private final int foto;
    private final int descripcion;
    private final PropertyChangeSupport ps;
    private final int sonido;
    Potenciador(String pNombre, int pCoste, Strategy s, int foto, int descripcion, int sonido){
        this.foto = foto;
        this.cantidad = 0;
        this.nombre = pNombre;
        this.coste = pCoste;
        this.s = s;
        this.descripcion = descripcion;
        ps = new PropertyChangeSupport(this);
        this.sonido = sonido;
    }
    public int getCoste() {
        return coste;
    }

    public int getCantidad() {
        return cantidad;
    }

    public String getNombre() {
        return nombre;
    }
    public void incrementarCantidad(){
        this.cantidad++;
        ps.firePropertyChange("Canti",this.cantidad,null);
    }
    public void decrementarCantidad(){
        this.cantidad--;
    }
    public boolean tienesMonedos(){
        return Partida.getInstance().getMonedas()>=this.coste;
    }
    public Strategy getStrategy() {
        return s;
    }

    public int getFoto() {
        return foto;
    }
    public int getDescripcion(){
        return this.descripcion;
    }
    public int getSonido(){
        return this.sonido;
    }
    public void addPropertyChangeListener(PropertyChangeListener pl){
        ps.addPropertyChangeListener(pl);
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
