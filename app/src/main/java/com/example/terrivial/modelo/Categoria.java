package com.example.terrivial.modelo;
import static java.util.stream.IntStream.range;
import android.content.Context;

import com.example.terrivial.vista.MainActivity;

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
    private String nombre;
    private Map<String, List<Pregunta>> pregunticas;
    private Map<String, Boolean> punticos;

    public Categoria() {
        puntoConseguido = false;
        pregunticas = new HashMap<>();
        punticos = new HashMap<>();
        nombre = this.getClass().toString().substring(35);
    }
    public String getNombre(){
        return this.nombre;
    }
    public Pregunta preguntaRandom() {
        List<Pregunta> p = (List<Pregunta>) (pregunticas.values().toArray()[new Random().nextInt(pregunticas.keySet().size())]);
        return p.get(new Random().nextInt(p.size()));
    }

    public Map<String, List<Pregunta>> getPregunticas() {
        return this.pregunticas;
    }

    public void anadirLista(String subCateg, List<Pregunta> lista) {
        this.pregunticas.put(subCateg, lista);
    }

    public void asignarPunticos(String subCateg, boolean puntico) {
        punticos.put(subCateg, puntico);
        if (punticos.values().stream().anyMatch(i -> !i))
            this.puntoConseguido = true;
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
        }

}
