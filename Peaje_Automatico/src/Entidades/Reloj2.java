/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import Entidades.*;
import Logger.Logger;
import java.time.LocalDateTime;

/**
 *
 * @author Agustin
 */
public class Reloj2 {

    private int numero_de_ciclo = 0;
    private final Boolean[] listaDeHilos = new Boolean[3];//modificar acorde a tamaño

    public Reloj2() {
        // this.listaDeHilos[0] = false;//caja de frecuencias este
        this.listaDeHilos[0] = true;//caja de frecuencias oeste
        this.listaDeHilos[1] = true;//pivot
        this.listaDeHilos[2] = true;//cabina 1
        //this.listaDeHilos[4] = false;//habilitador de cabinas
    }

    public synchronized void hiloEjecutado(int i) {
        listaDeHilos[i] = false;
        chequearEstados();
    }

    /**
     * le dice a los hilos si pueden ejecutarse nuevamente
     *
     * @param estadoHilo
     * @return
     */
    public synchronized boolean nuevoCiclo(int id) {
        return listaDeHilos[id];
    }

    /**
     * se fija que todos los hilos hayan sido ejecutados. Si lo fueron, cambia
     * el estado previo para un nuevo ciclo y notifica a todos los ciclos
     *
     * @return
     */
    public synchronized void chequearEstados() {
        boolean cambioDeEstado = true;
        for (Boolean estadoHilo : listaDeHilos) {
            if (estadoHilo == true) {
                cambioDeEstado = false;
            }
        }
        if (cambioDeEstado) {
            Logger.agregarLog(this.getNumero_de_ciclo() + ","
                    + Thread.currentThread().getId() + ","
                    + "Reloj,chequeaEstado, "
                    + "El reloj cambia de estado," + LocalDateTime.now());
            for (Boolean estadoHilo : listaDeHilos) {
                estadoHilo = true;
            }
            agregarCiclo();
            System.out.println("nuevo ciclo");
            this.notifyAll();
        }
    }

    public void agregarCiclo() {
        this.numero_de_ciclo++;
        Logger.agregarLog(this.getNumero_de_ciclo() + ","
                + Thread.currentThread().getId() + ","
                + "Reloj,checkearEstados, CICLO NUMERO  "
                + numero_de_ciclo + " HA COMENZADO!," + LocalDateTime.now());
    }

    public int getNumero_de_ciclo() {
        return numero_de_ciclo;
    }

}
