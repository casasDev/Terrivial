package com.example.terrivial.modelo;

import static java.util.stream.IntStream.range;

import android.content.Context;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public abstract class Categoria {
    private boolean puntoConseguido;
    private final String nombre;
    private final Map<String, List<Pregunta>> pregunticas;
    private final Map<String, Boolean> punticos;
    private final PropertyChangeSupport p = new PropertyChangeSupport(this);
    private int puntosAsignados;
    public Categoria() {
        puntoConseguido = false;
        pregunticas = new HashMap<>();
        punticos = new HashMap<>();
        nombre = this.getClass().toString().substring(35);
    }
    public void addPropertyChangeListener(PropertyChangeListener l){
        this.p.addPropertyChangeListener(l);
    }
    public String getNombre(){
        return this.nombre;
    }
    public Pregunta preguntaRandom() {
        List<Pregunta> p = (List<Pregunta>)(pregunticas.values().toArray()[new Random().nextInt(pregunticas.keySet().size())]);
        return p.get(new Random().nextInt(p.size()));
    }

    public Map<String, List<Pregunta>> getPregunticas() {
        return this.pregunticas;
    }
    public Map<String ,Boolean> getPunticos(){
        return this.punticos;
    }
    public void anadirLista(String subCateg, List<Pregunta> lista) {
        this.pregunticas.put(subCateg, lista);
    }

    public void asignarPunticos(String subCateg, boolean puntico) {
        if(puntico) {
            punticos.put(subCateg, true);
            p.firePropertyChange("subPuntico", subCateg, false);
        } else{
            punticos.keySet().forEach(c -> punticos.put(c, false));
            p.firePropertyChange("fallaste", punticos.keySet(),false);
        }
        if (punticos.values().stream().allMatch(i -> i)) {
            this.puntoConseguido = true;
            Partida.getInstance().anadirPuntos(this.puntosAsignados);
            p.firePropertyChange("puntoConseguido", this.nombre, this);
        }
    }
    private List<Pregunta> leerFichero(String subCateg, Context c) throws IOException {
        List<Pregunta> lista = new ArrayList<>();
        new BufferedReader(
                new InputStreamReader(
                        c.getAssets().open("Pregunticas/"
                        +this.getNombre()+
                        "/"+subCateg+".txt"), StandardCharsets.UTF_8))
                .lines().forEach(l ->{
                    String[] s = l.split(",");
                    Pregunta p = new Pregunta(s[1], subCateg);
                    range(0,s.length-2).forEach(i -> p.anadirRespuesta(s[s.length-(i+1)]));
                    lista.add(p);

                });
        return lista;
    }
        public void llenarMapa(Context c){
            this.pregunticas.keySet().forEach(ca ->{
                this.punticos.put(ca,false);
                try {
                    this.anadirLista(ca, leerFichero(ca,c));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            this.puntosAsignados = this.pregunticas.keySet().size();
        }

}
