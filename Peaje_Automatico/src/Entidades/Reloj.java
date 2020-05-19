/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import Logger.Logger;
import java.util.ArrayList;

/**
 *
 * @author Agustin
 */
public class Reloj {

    private int numero_de_ciclo = 0;
    private boolean estadoPrevio = true;
    private Boolean[] listaDeHilos = new Boolean[8];//modificar acorde a tamaño

    public Reloj() {
        this.listaDeHilos[0] = false;//pivot este
        this.listaDeHilos[1] = false;//pivot oeste
        this.listaDeHilos[2] = false;//caja de frecuencias este
        this.listaDeHilos[3] = false;//caja de frecuencias oeste
        this.listaDeHilos[4] = false;//cabina1
        this.listaDeHilos[5] = false;//cabina2
        this.listaDeHilos[6] = false;//cabina3
        this.listaDeHilos[7] = false;//cabina4
    }

    public void hiloEjecutado(int i) {
        if (listaDeHilos[i] == false) {
            this.listaDeHilos[i] = true;
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
        if (estadoHilo != estadoPrevio) {
            return true;
        }
        return false;
    }

    /**
     * se fija que todos los hilos hayan sido ejecutados. Si lo fueron, cambia
     * el estado previo para un nuevo ciclo y notifica a todos los ciclos
     */
    public synchronized boolean chequearEstados() {
        boolean cambiarEstado =true;
        for (Boolean estadoHilo : listaDeHilos) {
            if(estadoHilo!= estadoPrevio){
                cambiarEstado = false;
                break;
            }
        }
        if (cambiarEstado) {
            if (estadoPrevio == true) {
                estadoPrevio = false;
            } else {
                estadoPrevio = true;
            }
            agregarCiclo();
            Logger.log(this.getNumero_de_ciclo()+","+
                Thread.currentThread().getId()+","+
                "Reloj,checkearEstados, CICLO NUMERO  "
                    + numero_de_ciclo+" HA COMENZADO!");
            return true;
            
        }
        return false;
    }
    public void agregarCiclo(){
        this.numero_de_ciclo++;
    }

    public int getNumero_de_ciclo() {
        return numero_de_ciclo;
    }

}
