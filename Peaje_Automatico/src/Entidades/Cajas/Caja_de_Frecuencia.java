/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades.Cajas;

import Entidades.Colas.Cola_Comun_Ruta;
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
    public Caja_de_Frecuencia(int autos_per_minute, Reloj r) {
        super();
        this.reloj = r;
        this.autos_por_minuto = autos_per_minute;
        this.id_de_hilo=1;
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
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            if (Caja_de_vehiculos.cola.isEmpty() != true) {
                try {
                    Thread.sleep(autos_por_minuto);
                } catch (InterruptedException ex) {
                    
                }
                Vehiculo v = Caja_de_vehiculos.cola.poll();
                v.setHoraEntrada(System.nanoTime());//Se inicia la hora de entrada al sistema
                if(v!=null){Cola_Comun_Ruta.cola.add(v);}
                Logger.log(reloj.getNumero_de_ciclo()+","+
                Thread.currentThread().getId()+","+"Caja_de_Frecuencia,run, El vehiculo " + v.getMatricula() + " ha llegado por la ruta!");
            }
            reloj.hiloEjecutado(id_de_hilo);
            cambiarEstado();
        }
    }
    public void cambiarEstado() {
        if (estado == true) {
            estado = false;
        } else {
            estado = true;
        }
        synchronized (reloj) {
            if (reloj.chequearEstados()) {
                reloj.notifyAll();
            }
        }
    }
}
