/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Usuario
 */
public class HabilitadorDeCabinas extends Thread {
    private final Reloj reloj;
    private final int id_de_hilo;
    private boolean estado;

    public HabilitadorDeCabinas(Reloj r, int idHilo) {
        super();
        this.reloj = r;
        this.id_de_hilo = idHilo;
        this.estado = false;
    }
    
    @Override
    public void run() {
        while (Proyecto_peaje.cantidadEntrada > Proyecto_peaje.cantidadSalida) {
            if (reloj.nuevoCiclo(estado) != true) {
                try {
                    synchronized (reloj) {
                        reloj.wait();
                    }
                } catch (InterruptedException e) {
                }
            }
            for(Cabina cabina : Peaje.getListaCabinas() ){
                    if(!cabina.getHabilitada()){
                        cabina.setHabilitada(true);
                    }
                }
            reloj.hiloEjecutado(id_de_hilo);
//            try {
//                cambiarEstado();
//            } catch (InterruptedException ex) {
//                java.util.logging.Logger.getLogger(Cabina.class.getName()).log(Level.SEVERE, null, ex);
//            }

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