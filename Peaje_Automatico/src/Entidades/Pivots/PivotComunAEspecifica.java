/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades.Pivots;

import Entidades.Cabina;
import Entidades.Reloj;
import Entidades.Colas.Cola_Comun_Ruta;
import Entidades.Colas.Colas_Vehiculos_Clasificados;
import Entidades.Proyecto_peaje;
import Entidades.Vehiculo;
import java.util.Queue;
import java.util.logging.Level;
import Logger.Logger;

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
    private int id_de_hilo;
    private String direccion;

    /**
     *
     * @param retraso
     *
     * 20 ms equivale a 30 segundos de la vida real
     */
    public PivotComunAEspecifica(int retraso, Reloj r, int idHilo, String dir/*, Queue<Vehiculo> a, Queue<Vehiculo> b, Queue<Vehiculo> c, Queue<Vehiculo> d*/) {
        super();
        this.reloj = r;
        this.retraso_por_vehiculos_especiales = retraso;
        this.direccion = dir;
        this.id_de_hilo = idHilo;
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
            Vehiculo v = Cola_Comun_Ruta.getVehiculo(direccion);
            if (v != null) {
                Colas_Vehiculos_Clasificados.agregarVehiculo(v);
                Logger.log(reloj.getNumero_de_ciclo() + ","
                        + Thread.currentThread().getId() + ","
                        + "PivotComunAEspecifica,run, "
                        + "El vehiculo especial de matricula: "
                        + v.getMatricula() + " se posiciona en la"
                        + " cola de vehiculos," + v.getDireccion());
            }
            reloj.hiloEjecutado(id_de_hilo);
            try {
                cambiarEstado();
            } catch (InterruptedException ex) {
                java.util.logging.Logger.getLogger(PivotComunAEspecifica.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void cambiarEstado()throws InterruptedException {
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
