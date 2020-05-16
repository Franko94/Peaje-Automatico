/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;

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
public class Caja_de_Frecuencia implements Runnable {

    private int autos_por_minuto;
    private Caja_de_vehiculos caja_de_vehiculos;

    /**
     * @param autos_per_minute Este parametro debe ser seteado de acuerdo a lo
     * siguiente <p> 
     * Tiempo standar 6 autos / min >> 7ms <p>
     * Demanda alta 20autos/ min>> 2 ms <p>
     * Picos de trafico 50autos / min >> 1 ms
     */
    public Caja_de_Frecuencia(int autos_per_minute) {
        this.autos_por_minuto = autos_per_minute;
    }

    @Override
    public void run() {
        while (Caja_de_vehiculos.cola.isEmpty() != true) {
            try {
                Thread.sleep(autos_por_minuto);
            } catch (InterruptedException ex) {
                Logger.getLogger(Caja_de_Frecuencia.class.getName()).log(Level.SEVERE, null, ex);
            }
            Vehiculo v = Caja_de_vehiculos.cola.poll();
            v.setHoraEntrada(System.nanoTime());//Se inicia la hora de entrada al sistema
            Cola_Comun_Ruta.cola.add(v);
        }
    }

}
