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
import java.util.Queue;
import java.util.logging.Level;
import Logger.Logger;

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

    private Reloj reloj;
    private boolean estado = false;
    private int autos_por_minuto;
    private int id_de_hilo;
    private int timempoInicial;
    private String direccion;

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

        while (true) {
            if (reloj.nuevoCiclo(estado) != true) {
                try {
                    synchronized (reloj) {
                        reloj.wait();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if (Caja_de_vehiculos.estaVacia(direccion) != true) {
                int tiempoActual = (int) System.currentTimeMillis();
                if (tiempoActual - timempoInicial > autos_por_minuto) {
                    timempoInicial = tiempoActual;
                    Vehiculo v = Caja_de_vehiculos.getVehiculo(direccion);
                    v.setHoraEntrada(tiempoActual);//Se inicia la hora de entrada al sistema
                    if (v != null) {
                        Cola_Comun_Ruta.agregarVehiculo(v);
                    }
                    Logger.log(reloj.getNumero_de_ciclo() + ","
                            + Thread.currentThread().getId() + "," + "Caja_de_Frecuencia,run, El vehiculo " + v.getMatricula() + " ha llegado por la ruta!");
                }
            }

            reloj.hiloEjecutado(id_de_hilo);
            try {
                cambiarEstado();
            } catch (InterruptedException ex) {
                java.util.logging.Logger.getLogger(Caja_de_Frecuencia.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    public synchronized void cambiarEstado() throws InterruptedException{
        if (estado == true) {
            estado = false;
        } else {
            estado = true;
        }
        synchronized (reloj) {
            if (reloj.chequearEstados() == true) {
                reloj.notifyAll();
            }
        }
    }
}
