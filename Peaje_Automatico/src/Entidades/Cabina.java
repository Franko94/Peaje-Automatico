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
public class Cabina extends Thread {

    private final Reloj2 reloj;
    private boolean habilitada = true;
    private final int id_de_hilo;
    private final String direccion; // esto hay que verlo
    private boolean estado;
    private final int contador = 0;

    public Cabina(int idHilo, Reloj2 r, String dir) {
        super();
        this.reloj = r;
        this.direccion = dir;
        this.id_de_hilo = idHilo;
        this.estado = false;
    }

    public boolean getHabilitada() {
        return habilitada;
    }

    public void setHabilitada(boolean habilitada) {
        this.habilitada = habilitada;
    }

    @Override
    public void run() {
        while (Proyecto_peaje.cantidadEntrada > Proyecto_peaje.cantidadSalida) {
            System.out.println("nuevo ciclo de cabina");
            if (!reloj.nuevoCiclo(id_de_hilo)) {
                System.out.println("cabina en espera");
                try {
                    synchronized (reloj) {
                        reloj.wait();
                        System.out.println("cabina ejecuta");
                        if (getHabilitada()) {
                            Vehiculo v = Colas_Vehiculos_ManualesyAutomaticos.getVehiculo(direccion);
                            if (v != null) {
                                System.out.println(v.pasar_a_String());
                                try {
                                    Logger.agregarVehiculo(v.pasar_a_String());
                                    Thread.currentThread().sleep(1);
                                } catch (InterruptedException ex) {
                                    java.util.logging.Logger.getLogger(Cabina.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                if (v.getGeneraAccidente()) {
                                    setHabilitada(false);
                                }
                            }

                        }
                        reloj.hiloEjecutado(id_de_hilo);
                    }
                } catch (InterruptedException e) {
                }
                System.out.println("salio del wait la cabina");
            }

        }
    }

//    public void cambiarEstado() throws InterruptedException {
//        estado = estado != true;
//        synchronized (reloj) {
//            if (reloj.chequearEstados() == true) {
//                reloj.notifyAll();
//            }
//        }
//    }
}
