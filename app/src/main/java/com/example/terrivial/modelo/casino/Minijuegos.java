package com.example.terrivial.modelo.casino;

import com.example.terrivial.R;

public enum Minijuegos {
    AHORCADO(R.drawable.ahorcadito, "Ahorcado", "Creo que todo el mundo conoce este juego, si aciertas la palabra se te duplican las monedas"), ADIVINARNUMERO(R.drawable.adivinas, "Adivinar el número", "Este minijuego es muy simple, se generará internamente un número del cero al nueve, tendrás dos oportunidades para adivinarlo. Si fallas adiós monedas, si aciertas se te duplicará la cantidad que hayas introducido"), BATTLESHIP(R.drawable.battleship,"Hundir la flota","Si ganas se te duplican las monedas. Clásico hundir la flota no tiene misterio");
    private final int foto;
    private final String nombre;
    private final String descripcion;

    Minijuegos(int foto, String nombre, String descripcion) {
        this.foto = foto;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public int getFoto() {
        return foto;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
