package com.example.terrivial.modelo.casino.battleship;

import android.util.Log;

import com.example.terrivial.modelo.Jugador;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class Usuario {
    private Tablero tableroPropio;
    private Tablero tableroRival;
    private final List<Barco> listaBarcos;
    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
    protected Usuario(){
        listaBarcos = new ArrayList<>();
        tableroPropio = new Tablero();
    }
    public void addPCL(PropertyChangeListener pcl){
        this.pcs.addPropertyChangeListener(pcl);
    }
    protected void disparar(int x, int y, List<Barco> listaBarcos) {
        Casilla objetivo = this.getTableroRival().getCasilla(x,y);
        if(objetivo.getEstado()==TipoEstado.BARCO){
            objetivo.setEstado(TipoEstado.TOCADO);
            this.getPcs().firePropertyChange("Tocado",objetivo,this);
            Barco tocado = listaBarcos.stream().filter(b -> b.getListaCasillas().contains(objetivo)).findFirst().orElse(null);
            if(tocado.estaHundido()){
                tocado.hundir();
                this.getPcs().firePropertyChange("Hundido",tocado.getListaCasillas(),this);
                Log.d("XDXDXD", flotaHundida() +","+(this instanceof JugadorBattleship));
                if(flotaHundida()) this.getPcs().firePropertyChange("Derrota",this,0);
            }
        } else{
            objetivo.setEstado(TipoEstado.AGUA);
            this.getPcs().firePropertyChange("Agua",objetivo,this);
        }
    }
    protected void generarYColocarBarcos(int tamanioMax){
        for(int i = tamanioMax; 1 <= i; i--){
            for(int j = tamanioMax; i <= j; j--){
                colocarbarcoDeTamanio(i);
            }
        }
    }
    private void colocarbarcoDeTamanio (int t){
        Random random = new Random();
        boolean horizontal = random.nextBoolean();
        int fila;
        int columna;

        do {
            fila = random.nextInt(Tablero.getFilas());
            columna = random.nextInt(Tablero.getColumnas());
        } while (!esPosicionValida(fila, columna, t, horizontal));

        Barco barco = new Barco(t, horizontal);
        for (int i = 0; i < t; i++) {
            Casilla casilla = tableroPropio.getCasilla(fila,columna);
            casilla.setEstado(TipoEstado.BARCO);
            if(this instanceof JugadorBattleship) this.pcs.firePropertyChange("Barco",casilla,0);
            barco.anadirCasilla(casilla);
            if (horizontal) {
                columna++;
            } else {
                fila++;
            }
        }
        listaBarcos.add(barco);

    }
    private boolean esPosicionValida(int fila, int columna, int tamanio, boolean horizontal){
        int filas = Tablero.getFilas();
        int columnas = Tablero.getColumnas();
        if (horizontal) {
            if (columna + tamanio > columnas) {
                return false;
            }
            for (int i = 0; i < tamanio; i++) {
                if (tableroPropio.getCasilla(fila,columna + i).getEstado() ==TipoEstado.BARCO ||
                        (fila > 0 && tableroPropio.getCasilla(fila - 1,columna + i).getEstado() ==TipoEstado.BARCO) ||
                        (fila < filas - 1 && tableroPropio.getCasilla(fila + 1,columna + i).getEstado() ==TipoEstado.BARCO) ||
                        ( columna + i > 0 && tableroPropio.getCasilla(fila,columna + i - 1).getEstado() == TipoEstado.BARCO) ||
                        (columna + i < columnas - 1 && this.tableroPropio.getCasilla(fila,columna + i + 1).getEstado() == TipoEstado.BARCO)) {
                    return false;
                }
            }
        } else {
            if (fila + tamanio > filas) {
                return false;
            }
            for (int i = 0; i < tamanio; i++) {
                if (tableroPropio.getCasilla(fila + i,columna).getEstado() ==TipoEstado.BARCO ||
                        (fila + i > 0 && getTableroPropio().getCasilla(fila + i - 1,columna).getEstado() == TipoEstado.BARCO) ||
                        (fila + i < filas - 1 && tableroPropio.getCasilla(fila + i + 1,columna).getEstado() == TipoEstado.BARCO) ||
                        (columna > 0 && tableroPropio.getCasilla(fila + i,columna - 1).getEstado() ==TipoEstado.BARCO) ||
                        (columna < columnas - 1 && tableroPropio.getCasilla(fila + i,columna + 1).getEstado() ==TipoEstado.BARCO)) {
                    return false;
                }
            }
        }
        return true;

    }

    public Tablero getTableroPropio() {
        return tableroPropio;
    }
    public void setTableroRival(Tablero tableroRival){
        this.tableroRival = tableroRival;
    }

    public Tablero getTableroRival() {
        return this.tableroRival;
    }

    public List<Barco> getListaBarcos() {
        return this.listaBarcos;
    }

    public PropertyChangeSupport getPcs() {
        return this.pcs;
    }
    public boolean flotaHundida(){
        return this.listaBarcos.stream().allMatch(Barco::estaHundido);
    }
}
