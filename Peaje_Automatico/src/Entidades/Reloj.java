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
    private final Boolean[] listaDeHilos = new Boolean[13];//modificar acorde a tamaño

    public Reloj() {
        this.listaDeHilos[0] = false;//caja de frecuencias este
        this.listaDeHilos[1] = false;//caja de frecuencias oeste
        this.listaDeHilos[2] = false;//pivot este
        this.listaDeHilos[3] = false;//cabina 1
        this.listaDeHilos[4] = false;//habilitador de cabinas
        this.listaDeHilos[5] = false;//cabina2
        this.listaDeHilos[6] = false;//cabina3
        this.listaDeHilos[7] = false;//cabina4
        this.listaDeHilos[8] = false;//pivot oeste
        this.listaDeHilos[9] = false;//telepeaje este
        this.listaDeHilos[10] = false;//telepeaje oeste
        this.listaDeHilos[11] = false;//telepeaje este2
        this.listaDeHilos[12] = false;//telepeaje oeste2
    }

    public synchronized void hiloEjecutado(String clase, int i) {
        Logger.agregarLog(this.getNumero_de_ciclo() + "," + clase + "," + i + ","
                + "Reloj,hiloEjecutado,"
                + "El reloj recibe al hilo como ejecutado," + LocalDateTime.now());
        if (listaDeHilos[i] == false) {
            this.listaDeHilos[i] = true;
        } else {
            listaDeHilos[i] = false;
        }
        chequearEstados(clase, i);

    }

    /**
     * le dice a los hilos si pueden ejecutarse nuevamente
     *
     * @param estadoHilo
     * @return
     */
    public synchronized boolean nuevoCiclo(int id, String clase) {
        Logger.agregarLog(this.getNumero_de_ciclo() + ","
                + clase.concat("," + id) + ","
                + "Reloj,nuevoCiclo, "
                + "El reloj comprueba si el hilo puede dar un nuevo ciclo," + LocalDateTime.now());
        if (listaDeHilos[id] != estadoPrevio) {

            return true;
        }
        Logger.agregarLog(this.getNumero_de_ciclo() + ","
                + clase.concat("," + id) + ","
                + "Reloj,nuevoCiclo, "
                + "No se puede dar un nuevo ciclo faltan ejecutar hilos,"+ LocalDateTime.now());
        return false;
    }

    /**
     * se fija que todos los hilos hayan sido ejecutados. Si lo fueron, cambia
     * el estado previo para un nuevo ciclo y notifica a todos los ciclos
     *
     * @return
     */
    public void chequearEstados(String clase, int i) {
        Logger.agregarLog(this.getNumero_de_ciclo() + "," + clase + "," + i + ","
                + "Reloj,chequeaEstado, "
                + "El reloj comprueba estados," + LocalDateTime.now());
        boolean cambiarEstado = true;
        for (Boolean estadoHilo : listaDeHilos) {
            if (estadoHilo != estadoPrevio) {
                cambiarEstado = false;
                break;
            }
        }
        if (cambiarEstado) {
            Logger.agregarLog(this.getNumero_de_ciclo() + "," + clase + "," + i + ","
                    + "Reloj,chequeaEstado, "
                    + "El reloj cambia de estado," + LocalDateTime.now());
            estadoPrevio = estadoPrevio != true;
            agregarCiclo(clase, i);
            this.notifyAll();

        }
    }

    public void agregarCiclo(String clase, int i) {
        this.numero_de_ciclo++;
        Logger.agregarLog(this.getNumero_de_ciclo() + "," + clase + "," + i + ","
                + "Reloj,chequearEstados, CICLO NUMERO  "
                + numero_de_ciclo + " HA COMENZADO!," + LocalDateTime.now());
    }

    public int getNumero_de_ciclo() {
        return numero_de_ciclo;
    }
}
