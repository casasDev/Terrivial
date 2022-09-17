package com.example.terrivial.modelo;

public class Ciencia extends Categoria{
    private static Ciencia mCiencia;
    private Ciencia(){
     super();
     this.anadirLista("Sumar",null);
     this.anadirLista("Dividir",null);
     this.anadirLista("Multiplicar",null);
    }
    public static Ciencia getInstance(){
        if(mCiencia == null) mCiencia = new Ciencia();
        return mCiencia;
    }
}
