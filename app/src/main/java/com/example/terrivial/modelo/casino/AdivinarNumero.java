package com.example.terrivial.modelo.casino;

import java.util.Random;

public class AdivinarNumero extends Minijuego{
    private final int num = new Random().nextInt(10)+1;
    private int vidas = 2;
    public AdivinarNumero(int apuesta) {
        super(apuesta, 0, "Este minijuego es muy simple, se generará internamente un número del uno al 10, tendrás dos oportunidades para adivinarlo. Si fallas adiós monedas, si aciertas se te duplicará la cantidad que hayas introducido","Adivina el número");
    }

    public void ejecutar(int num2) {
        if(num2<1 || num2>10){
            this.getPcs().firePropertyChange("Tonto",null,null);
        } else if(!comprobarNumero(num2)){
            vidas--;
            if (vidas == 0){
               this.getPcs().firePropertyChange("Sin vidas",null,null);
            }
            else this.getPcs().firePropertyChange("Numero incorrecto",this.vidas,null);
        } else{
            apuestaLograda();
            this.getPcs().firePropertyChange("Apuesta lograda",null,null);
        }
    }
    public boolean comprobarNumero(int num2){
        return this.num == num2;
    }

}
