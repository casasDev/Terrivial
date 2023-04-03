package com.example.terrivial.modelo.casino;

import java.util.Random;

public class AdivinarNumero extends Minijuego{
    private final int num = new Random().nextInt(10);
    private int vidas = 2;
    public AdivinarNumero(int apuesta) {
        super(apuesta, 0, "Este minijuego es muy simple, se generará internamente un número del cero al nueve, tendrás dos oportunidades para adivinarlo. Si fallas adiós monedas, si aciertas se te duplicará la cantidad que hayas introducido","Adivina el número");
    }

    public void ejecutar(int num2) {
         if(!comprobarNumero(num2)){
            vidas--;
            if (vidas == 0){
               apuestaAcabada(false);
            }
            else this.getPcs().firePropertyChange("Numero incorrecto",this.vidas,null);
        } else{
            apuestaAcabada(true);
        }
    }
    public boolean comprobarNumero(int num2){
        return this.num == num2;
    }

    public int getVidas() {
        return vidas;
    }
    public int getNum(){
        return this.num;
    }
}
