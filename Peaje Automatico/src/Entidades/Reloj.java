/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.util.ArrayList;

/**
 *
 * @author Agustin
 */
public class Reloj {

    private int numero_de_ciclo = 0;
    private boolean estadoPrevio = true;
    private Boolean[] listaDeHilos = new Boolean[2];

    public Reloj() {
        listaDeHilos[0] = false;
        listaDeHilos[1] = false;
    }

    public void hiloEjecutado(int i) {
        if (listaDeHilos[i] == false) {
            listaDeHilos[i] = true;
        } else {
            listaDeHilos[i] = false;
        }
    }

    /**
     * le dice a los hilos si pueden ejecutarse nuevamente
     *
     * @param estadoHilo
     * @return
     */
    public boolean nuevoCiclo(boolean estadoHilo) {
        numero_de_ciclo++;
        if (estadoHilo != estadoPrevio) {
            return true;
        }
        return false;
    }

    /**
     * se fija que todos los hilos hayan sido ejecutados. Si lo fueron, cambia
     * el estado previo para un nuevo ciclo y notifica a todos los ciclos
     */
    public boolean chequearEstados() {
        
        if (listaDeHilos[0] == estadoPrevio & listaDeHilos[1] == estadoPrevio & listaDeHilos[2]== estadoPrevio) {
            if (estadoPrevio == true) {
                estadoPrevio = false;
            } else {
                estadoPrevio = true;
            }
            return true;
        }
        return false;
    }

}
