package com.example.terrivial.modelo.casino;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Ahorcado extends Minijuego {
    private final String palabra;
    private final char[] palDest;
    private final char[] palDestG;
    private final List<Character> listaNegra;
    private static final int vidasIniciales = 7;
    private int vidas = vidasIniciales;
    private static final char[] abecedario = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'Ñ', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

    public Ahorcado(int apuesta) {
        super(apuesta, 0, "Creo que todo el mundo conoce este juego, si aciertas la palabra se te duplican las monedas", "Ahorcado");
        listaNegra = new ArrayList<>();
        palabra = ListaPalabras.getMiLista().palabraRandom();
        palDest = palabra.toCharArray();
        palDestG = generarGuiones();
    }

    public int getVidas() {
        return vidas;
    }
    public static int getVidasIniciales(){
        return vidasIniciales;
    }
    public static char[] getAbecedario() {
        return abecedario;
    }

    public void ejecutar(String s) {
        char c = s.charAt(0);
        if (comprobarLetra(c)) {
            this.getPcs().firePropertyChange("Letra acertada", this.toString(), null);
            if (hasAcabado()) apuestaAcabada(true);
        } else {
            vidas--;
            listaNegra.add(c);
            this.getPcs().firePropertyChange("Letra fallada", c, vidas);
            if (vidas == 0) apuestaAcabada(false);
        }
    }

    public static void llenarLista(Stream<String> stream) {
        ListaPalabras.getMiLista().llenarLista(stream);
    }

    @Override
    protected String getRespuesta() {
        return this.palabra;
    }

    private char[] generarGuiones() {
        char[] c = new char[palDest.length];
        IntStream.range(0, c.length).forEach(i -> c[i] = '_');
        return c;
    }

    private boolean comprobarLetra(char c) {
        AtomicBoolean b = new AtomicBoolean(false);
        char finalC = Character.toLowerCase(c);
        IntStream.range(0, palDestG.length).forEach(i -> {
            if (palDest[i] == finalC) {
                b.set(true);
                palDestG[i] = finalC;
            } else if (palDest[i] == 'á' && finalC == 'a') {
                b.set(true);
                palDestG[i] = 'á';
            } else if (palDest[i] == 'é' && finalC == 'e') {
                b.set(true);
                palDestG[i] = 'é';
            } else if (palDest[i] == 'í' && finalC == 'i') {
                b.set(true);
                palDestG[i] = 'í';
            } else if (palDest[i] == 'ó' && finalC == 'o') {
                b.set(true);
                palDestG[i] = 'ó';
            } else if (palDest[i] == 'ú' && finalC == 'u') {
                b.set(true);
                palDestG[i] = 'ú';
            } else if (palDest[i] == 'ü' && finalC == 'u') {
                b.set(true);
                palDestG[i] = 'ü';
            }
        });
        return b.get();
    }

    private boolean hasAcabado() {
        return new String(palDestG).chars().mapToObj(i -> (char) i).allMatch(c -> c != '_');
    }

    private static class ListaPalabras {
        private final List<String> listaPalabras;
        private static ListaPalabras miLista;

        private ListaPalabras() {
            listaPalabras = new ArrayList<>();
        }

        public void llenarLista(Stream<String> s) {
            s.forEach(listaPalabras::add);
        }

        public static ListaPalabras getMiLista() {
            if (miLista == null) {
                miLista = new ListaPalabras();
            }
            return miLista;
        }

        public String palabraRandom() {
            return listaPalabras.get(new Random().nextInt(646614) + 1);
        }
    }

    @Override
    public String toString() {
        return Arrays.toString(palDestG).replace("]", "").replace("[", "").replace(",", "");
    }
}
