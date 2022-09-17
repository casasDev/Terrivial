package com.example.terrivial.modelo;

public class Geopolitica extends Categoria{
    private static Geopolitica mGeopolitica;
    private Geopolitica(){
        super();
        this.anadirLista("Capitales", null);
        this.anadirLista("Presidentes",null);
    }
    public static Geopolitica getInstance(){
        if(mGeopolitica == null) mGeopolitica = new Geopolitica();
        return mGeopolitica;
    }
}
