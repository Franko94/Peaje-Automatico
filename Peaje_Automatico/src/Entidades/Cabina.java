/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import Entidades.Colas.Colas_Vehiculos_ManualesyAutomaticos;
import Logger.Logger;

/**
 *
 * @author apicos
 */
public class Cabina extends Thread {

    private final Reloj reloj;
    private boolean habilitada = true;
    private final int id_de_hilo;
    private final String direccion; // esto hay que verlo
    private boolean estado;
    private int contador = -1;

    public Cabina(int idHilo, Reloj r, String dir) {
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
            if (reloj.nuevoCiclo(id_de_hilo,"Cabina") != true) {
                try {
                    synchronized (reloj) {
                        reloj.wait(5);
                    }
                } catch (InterruptedException e) {
                }
            }
            if (getHabilitada()& contador<=0) {
                contador =10;
                Vehiculo v = Colas_Vehiculos_ManualesyAutomaticos.getManual(direccion);
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
            else{
                contador --;
            }
            reloj.hiloEjecutado("Cabina",id_de_hilo);
        }
    }
}
