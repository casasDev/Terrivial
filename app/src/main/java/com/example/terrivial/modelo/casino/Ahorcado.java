package com.example.terrivial.modelo.casino;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Ahorcado extends Minijuego{
    public Ahorcado(int apuesta) {
        super(apuesta, 0, "Creo que todo el mundo conoce este juego, si aciertas se te duplican las monedas", "Ahorcado");
    }
    public static class ListaPalabras{
       private final List<String> listaPalabras;
       private static ListaPalabras miLista;
       private ListaPalabras(){
           listaPalabras = new ArrayList<>();
       }
       public void llenarLista(Stream<String> s){
           s.forEach(listaPalabras::add);
       }

        public static ListaPalabras getMiLista() {
           if(miLista==null) {miLista = new ListaPalabras();}
            return miLista;
        }
        public String palabraRandom(){
           return listaPalabras.stream().findAny().orElse("polla");
        }
    }
}
