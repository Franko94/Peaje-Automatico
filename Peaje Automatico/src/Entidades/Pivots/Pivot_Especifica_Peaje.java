/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades.Pivots;

import Entidades.CabinaPeaje;
import Entidades.Colas.Colas_Vehiculos_Clasificados;
import Entidades.Reloj;
import Entidades.Vehiculo;
import java.util.Queue;

/**
 *
 * @author fran_
 */
/*
public class Pivot_Especifica_Peaje implements Runnable {

    public static Queue<CabinaPeaje> colaPeaje;
    private Reloj reloj;
    private boolean estado = false;

    public Pivot_Especifica_Peaje(Queue colaPeaje,Reloj r) {
         super();
        this.reloj = r;
        this.colaPeaje = colaPeaje;
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
        reloj.hiloEjecutado(0);
        cambiarEstado();
        distribuir();
        }
    }

    private void distribuir() {
        Vehiculo vehiculo;
        if (!Colas_Vehiculos_Clasificados.especiales.isEmpty()) {
            vehiculo = Colas_Vehiculos_Clasificados.especiales.poll();
        } else {
            vehiculo = Colas_Vehiculos_Clasificados.normales.poll();
        }
        for (CabinaPeaje cp : colaPeaje) {
            if (!cp.getOcupada()) {
                cp.setVehiculo(vehiculo);
                cp.setOcupada(true);
                break;
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
}*/
