/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import Logger.Logger;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author Agustin
 */
public class Reloj {

    private int numero_de_ciclo = 0;
    private boolean estadoPrevio = true;
    private Boolean[] listaDeHilos = new Boolean[4];//modificar acorde a tamaño

    public Reloj() {
        this.listaDeHilos[0] = false;//caja de frecuencias este
        this.listaDeHilos[1] = false;//caja de frecuencias oeste
        this.listaDeHilos[2] = false;//pivot este
        this.listaDeHilos[3] = false;//pivot oeste
    }

    public void hiloEjecutado(int i) {
//        Logger.log(this.getNumero_de_ciclo() + ","
//                        + Thread.currentThread().getId() + ","
//                        + "Reloj,hiloEjecutado, "
//                        + "El reloj recibe al hilo como ejecutado");
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
    public synchronized boolean nuevoCiclo(boolean estadoHilo) {
//        Logger.log(this.getNumero_de_ciclo() + ","
//                        + Thread.currentThread().getId() + ","
//                        + "Reloj,nuevoCiclo, "
//                        + "El reloj comprueba si el hilo puede dar un nuevo ciclo");
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
//        Logger.log(this.getNumero_de_ciclo() + ","
//                        + Thread.currentThread().getId() + ","
//                        + "Reloj,chequeaEstado, "
//                        + "El reloj comprueba estados");
        boolean cambiarEstado = true;
        for (Boolean estadoHilo : listaDeHilos) {
            if (estadoHilo != estadoPrevio) {
                cambiarEstado = false;
                break;
            }
        }
        if (cambiarEstado) {
//            Logger.log(this.getNumero_de_ciclo() + ","
//                        + Thread.currentThread().getId() + ","
//                        + "Reloj,chequeaEstado, "
//                        + "El reloj cambia de estado");
            if (estadoPrevio == true) {
                estadoPrevio = false;
            } else {
                estadoPrevio = true;
            }
            agregarCiclo();
//            Logger.log(this.getNumero_de_ciclo() + ","
//                    + Thread.currentThread().getId() + ","
//                    + "Reloj,checkearEstados, CICLO NUMERO  "
//                    + numero_de_ciclo + " HA COMENZADO!");
            return true;

        }
        return false;
    }

    public void agregarCiclo() {
        this.numero_de_ciclo++;
//                    Logger.log(this.getNumero_de_ciclo() + ","
//                    + Thread.currentThread().getId() + ","
//                    + "Reloj,checkearEstados, CICLO NUMERO  "
//                    + numero_de_ciclo + " HA COMENZADO!");
    }

    public int getNumero_de_ciclo() {
        return numero_de_ciclo;
    }
}
