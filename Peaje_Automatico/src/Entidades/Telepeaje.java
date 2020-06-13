/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import Entidades.Colas.Colas_Vehiculos_ManualesyAutomaticos;
import java.io.BufferedWriter;
import java.io.FileWriter;
import Logger.Logger;
import java.util.logging.Level;

/**
 *
 * @author apicos
 */
public class Telepeaje extends Thread {

    private final Reloj reloj;
    private final boolean habilitada = false;
    private final int id_de_hilo;
    private final String direccion;
    private boolean estado;

    public Telepeaje(int idHilo, Reloj r, String dir) {
        super();
        this.reloj = r;
        this.direccion = dir;
        this.id_de_hilo = idHilo;
        this.estado = false;
    }

    @Override
    public void run() {
        while (Proyecto_peaje.cantidadEntrada> Proyecto_peaje.cantidadSalida) {
            if (reloj.nuevoCiclo(estado) != true) {
                try {
                    synchronized (reloj) {
                        reloj.wait();
                    }
                } catch (InterruptedException e) {
                }
            }
            Vehiculo v = Colas_Vehiculos_ManualesyAutomaticos.getVehiculo(direccion);
            if (v != null) {
                System.out.println(v.pasar_a_String());
                try {
                    Logger.agregarVehiculo(v.pasar_a_String());
                    Thread.currentThread().sleep(1);
                } catch (InterruptedException ex) {
                    java.util.logging.Logger.getLogger(Telepeaje.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            reloj.hiloEjecutado(id_de_hilo);
            try {
                cambiarEstado();
            } catch (InterruptedException ex) {
                java.util.logging.Logger.getLogger(Telepeaje.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void cambiarEstado() throws InterruptedException {
        estado = estado != true;
        synchronized (reloj) {
            if (reloj.chequearEstados() == true) {
                reloj.notifyAll();
            }
        }
    }
}
