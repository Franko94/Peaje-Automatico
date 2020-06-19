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
    private boolean habilitada = false;
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
        while (Proyecto_peaje.cantidadEntrada > Proyecto_peaje.cantidadSalida) {
            if (reloj.nuevoCiclo(id_de_hilo,"telepeaje") != true) {
                try {
                    synchronized (reloj) {
                        reloj.wait(1);
                    }
                } catch (InterruptedException e) {
                }
            }
            if (getHabilitada()) {
                Vehiculo v = Colas_Vehiculos_ManualesyAutomaticos.getTelepeaje(direccion);
                if (v != null) {
                    int tiempoActual = (int) System.currentTimeMillis();
                    v.setHoraSalida(tiempoActual);
                    System.out.println(v.pasar_a_String());
                    Logger.agregarVehiculo(v.pasar_a_String());
                    if (v.getGeneraAccidente()) {
                        setHabilitada(false);
                    }
                }
            }
            reloj.hiloEjecutado("Telepeaje",id_de_hilo);
        }
    }

    public void setHabilitada(boolean habilitada) {
        this.habilitada = habilitada;
    }

    public boolean getHabilitada() {
        return habilitada;
    }
}
