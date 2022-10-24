package com.example.terrivial.modelo;

import android.graphics.Color;

public class Ciencia extends Categoria{
    private static Ciencia mCiencia;
    private Ciencia(){
     super(Color.GREEN);
     this.anadirLista("Sumar",null);
     this.anadirLista("Dividir",null);
     this.anadirLista("Multiplicar",null);
     this.anadirLista("Ciencia general",null);
    }
    public static Ciencia getInstance(){
        if(mCiencia == null) mCiencia = new Ciencia();
        return mCiencia;
    }
}
