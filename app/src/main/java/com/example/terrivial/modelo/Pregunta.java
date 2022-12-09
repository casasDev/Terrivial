package com.example.terrivial.modelo;

import java.util.ArrayList;
import java.util.List;

public class Pregunta {
    private final String enunciado;
    private final List<String> respuestas;
    private final String subCateg;

        public Pregunta(String enunciado, String subCateg){
        this.enunciado = enunciado;
        this.respuestas = new ArrayList<>();
        this.subCateg = subCateg;
        }
        public void anadirRespuesta(String respuesta){
            this.respuestas.add(respuesta);
        }
        public String toString(){
            return enunciado +" ||| "+ respuestas + " ||| " + subCateg;
        }
        public boolean respuestaCorrecta(String r){
           return r.equals(respuestas.get(respuestas.size()-1));
        }
        public String getEnunciado(){
            return this.enunciado;
        }
        public List<String> getRespuestas(){
            return this.respuestas;
        }
        public String getSubCateg(){
            return this.subCateg;
        }
}
