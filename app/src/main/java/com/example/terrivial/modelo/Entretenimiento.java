package com.example.terrivial.modelo;

import android.graphics.Color;

public class Entretenimiento extends Categoria{
    private static Entretenimiento mEntretenimiento;
        private Entretenimiento(){
            super(Color.MAGENTA);
            this.anadirLista("Canciones",null);
            this.anadirLista("Mejor actriz",null);
            this.anadirLista("Mejor actor", null);
            this.anadirLista("Mejor director",null);
            this.anadirLista("Grupos de música",null);
        }
        public static Entretenimiento getInstance(){
            if(mEntretenimiento == null) mEntretenimiento = new Entretenimiento();
            return mEntretenimiento;
        }
}
