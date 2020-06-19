/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades.Cajas;

import Entidades.Colas.Cola_Comun_Ruta;
import Entidades.Proyecto_peaje;
import Entidades.Reloj;
import Entidades.Vehiculo;
import java.util.logging.Level;
import Logger.Logger;
import java.time.LocalDateTime;

/**
 *
 * @author apicos
 */
/**
 *
 * Tiempo standar 6 autos / min >> 7ms Demanda alta 20autos/ min >> 2 ms Picos
 * de trafico 50autos / min >> 1 ms
 *
 * CAlculando un dia real = un minuto de simulacion: 24 minutos son 1 segundo de
 * simulacion 42 milisegundos = 1 minuto de simulacion
 *
 * @author Agustin
 */
public class Caja_de_Frecuencia extends Thread {

    private final Reloj reloj;
    private boolean estado = false;
    private final int autos_por_minuto;
    private final int id_de_hilo;
    private int timempoInicial;
    private final String direccion;

    /**
     * @param autos_per_minute Este parametro debe ser seteado de acuerdo a lo
     * siguiente
     * <p>
     * Tiempo standar 6 autos / min >> 7ms
     * <p>
     * Demanda alta 20autos/ min>> 2 ms
     * <p>
     * Picos de trafico 50autos / min >> 1 ms
     */
    public Caja_de_Frecuencia(int autos_per_minute, Reloj r, int idhilo, String dir) {
        super();
        this.reloj = r;
        this.autos_por_minuto = autos_per_minute;
        this.id_de_hilo = idhilo;
        this.timempoInicial = (int) System.currentTimeMillis();
        this.direccion = dir;

    }

    @Override
    public void run() {

        while (Proyecto_peaje.cantidadEntrada> Proyecto_peaje.cantidadSalida) {
            if (reloj.nuevoCiclo(id_de_hilo) != true) {
                try {
                    synchronized (reloj) {
                        reloj.wait(5);
                    }
                } catch (InterruptedException e) {
                }
            }
            if (Caja_de_vehiculos.estaVacia(direccion) != true) {
                int tiempoActual = (int) System.currentTimeMillis();
                if (tiempoActual - timempoInicial > autos_por_minuto) {
                    timempoInicial = tiempoActual;
                    Vehiculo v = Caja_de_vehiculos.getVehiculo(direccion);
                    v.setHoraEntrada(tiempoActual);//Se inicia la hora de entrada al sistema
                    Cola_Comun_Ruta.agregarVehiculo(v);
                    Logger.agregarLog(reloj.getNumero_de_ciclo() + ","
                            + Thread.currentThread().getId() + "," + "Caja_de_Frecuencia,run, El vehiculo " + v.getMatricula() + " ha llegado por la ruta!," + LocalDateTime.now());
                }
            }

            reloj.hiloEjecutado(id_de_hilo);
            Logger.agregarLog(reloj.getNumero_de_ciclo() + ","
                    + Thread.currentThread().getId() + ","
                    + "Caja de frecuencia,hiloEjecutado, "
                    + "la caja envia notificacion de hilo ejecutado," + LocalDateTime.now());
        }
    }

}
/**
 * hacer que la caja tire de forma alternada un auto para el carril 1 y 2 de la ruta comun
 */
