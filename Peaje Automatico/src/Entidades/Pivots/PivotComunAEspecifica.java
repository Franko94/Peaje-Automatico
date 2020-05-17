/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades.Pivots;

import Entidades.Reloj;
import Entidades.Colas.Cola_Comun_Ruta;
import Entidades.Colas.Colas_Vehiculos_Clasificados;
import Entidades.Vehiculo;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Teo
 */
/**
 * Dado que los hilos están sincronizados, el retraso por vehiculos especiales
 * se puede implementar aquí
 */
public class PivotComunAEspecifica extends Thread {

    private int retraso_por_vehiculos_especiales;
    private Reloj reloj;
    private boolean estado = false;
    private int id_de_hilo = 0;

    /**
     *
     * @param retraso
     *
     * 20 ms equivale a 30 segundos de la vida real
     */
    public PivotComunAEspecifica(int retraso, Reloj r) {
        super();
        this.reloj = r;
        this.retraso_por_vehiculos_especiales = retraso;

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
            reloj.hiloEjecutado(id_de_hilo);
            cambiarEstado();
            Vehiculo vehiculo = Cola_Comun_Ruta.cola.poll();
            if (vehiculo == null) {
                break;
            }
            if (vehiculo.isUnidad_especial()) {
                try {
                    Thread.sleep(retraso_por_vehiculos_especiales);
                } catch (InterruptedException ex) {
                    Logger.getLogger(PivotComunAEspecifica.class.getName()).log(Level.SEVERE, null, ex);
                }
                Colas_Vehiculos_Clasificados.especiales.add(vehiculo);
            } else {
                Colas_Vehiculos_Clasificados.normales.add(vehiculo);
            }
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
