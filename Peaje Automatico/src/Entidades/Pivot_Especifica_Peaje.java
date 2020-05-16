/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.util.Queue;

/**
 *
 * @author fran_
 */
public class Pivot_Especifica_Peaje implements Runnable {

    public static Queue <CabinaPeaje> colaPeaje;
    public static Queue <Vehiculo> colaNormales;
    public static Queue <Vehiculo> colaEspeciales;
    
    public Pivot_Especifica_Peaje(Queue colaPeaje, Queue colaNormales, Queue colaEspeciales){
        this.colaPeaje = colaPeaje;
        this.colaNormales = colaNormales;
        this.colaEspeciales = colaEspeciales;
    }

    @Override
    public void run() {
        distribuir();
    }
    
    private void distribuir(){
        Vehiculo vehiculo;
        if(!colaEspeciales.isEmpty()){
            vehiculo = colaEspeciales.poll();
        }
        else{
            vehiculo = colaNormales.poll();
        }
        for(CabinaPeaje cp : colaPeaje){
            if(!cp.getOcupada()){
                cp.setVehiculo(vehiculo);
                cp.setOcupada(true);
                break;
            }
        }
    }
}
