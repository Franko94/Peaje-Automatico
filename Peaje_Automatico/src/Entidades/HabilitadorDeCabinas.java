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
    private int contador = 30;

    public HabilitadorDeCabinas(Reloj r, int idHilo) {
        super();
        this.reloj = r;
        this.id_de_hilo = idHilo;
        this.estado = false;
    }
    
    @Override
    public void run() {
        while (Proyecto_peaje.cantidadEntrada > Proyecto_peaje.cantidadSalida) {
            if (reloj.nuevoCiclo(id_de_hilo) != true) {
                try {
                    synchronized (reloj) {
                        reloj.wait(1);
                    }
                } catch (InterruptedException e) {
                }
            }
            if(contador ==0){
                contador=30;
            for(Cabina cabina : Peaje.getListaCabinas() ){
                    if(!cabina.getHabilitada()){
                        cabina.setHabilitada(true);
                    }
                }
            for(Telepeaje telepeaje : Peaje.getListaTelepeaje()){
                    if(!telepeaje.getHabilitada()){
                        telepeaje.setHabilitada(true);
                    }
                }
            reloj.hiloEjecutado(id_de_hilo);}
            else{contador--;}
        }
    }
}