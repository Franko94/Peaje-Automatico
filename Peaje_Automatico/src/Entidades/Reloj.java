/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import Logger.Logger;
import java.time.LocalDateTime;

/**
 *
 * @author Agustin
 */
public class Reloj {

    private int numero_de_ciclo = 0;
    private boolean estadoPrevio = true;
    private final Boolean[] listaDeHilos = new Boolean[3];//modificar acorde a tamaño

    public Reloj() {
       // this.listaDeHilos[0] = false;//caja de frecuencias este
        this.listaDeHilos[0] = false;//caja de frecuencias oeste
        this.listaDeHilos[1] = false;//pivot
        this.listaDeHilos[2] = false;//cabina 1
        //this.listaDeHilos[4] = false;//habilitador de cabinas
    }

    public synchronized boolean hiloEjecutado(int i) {
        if (!listaDeHilos[i]) {
            this.listaDeHilos[i] = true;
        } else {
            listaDeHilos[i] = false;
        }
        chequearEstados();
        return listaDeHilos[i];
    }

    /**
     * le dice a los hilos si pueden ejecutarse nuevamente
     *
     * @param estadoHilo
     * @return
     */
    public synchronized boolean nuevoCiclo(boolean estadoHilo) {
        Logger.agregarLog(this.getNumero_de_ciclo() + ","
                        + Thread.currentThread().getId() + ","
                        + "Reloj,nuevoCiclo, "
                        + "El reloj comprueba si el hilo puede dar un nuevo ciclo");
        if (estadoHilo != estadoPrevio) {
            return true;
        }
        Logger.agregarLog(this.getNumero_de_ciclo() + ","
                        + Thread.currentThread().getId() + ","
                        + "Reloj,nuevoCiclo, "
                        + "No se puede dar un nuevo ciclo faltan ejecutar hilos");
        return false;
    }

    /**
     * se fija que todos los hilos hayan sido ejecutados. Si lo fueron, cambia
     * el estado previo para un nuevo ciclo y notifica a todos los ciclos
     * @return 
     */
    public synchronized void chequearEstados() {
        boolean cambiarEstado = true;
        for (Boolean estadoHilo : listaDeHilos) {
            if (estadoHilo != estadoPrevio) {
                cambiarEstado = false;
                break;
            }
        }
        if (cambiarEstado) {
            Logger.agregarLog(this.getNumero_de_ciclo() + ","
                        + Thread.currentThread().getId() + ","
                        + "Reloj,chequeaEstado, "
                        + "El reloj cambia de estado,"+ LocalDateTime.now());
            estadoPrevio = estadoPrevio != true;
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
                    + numero_de_ciclo + " HA COMENZADO!,"+ LocalDateTime.now());
    }

    public int getNumero_de_ciclo() {
        return numero_de_ciclo;
    }
    
}
