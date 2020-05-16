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
  
    
    public Pivot_Especifica_Peaje(Queue colaPeaje){
        this.colaPeaje = colaPeaje;
    }

    @Override
    public void run() {
        distribuir();
    }
    
    private void distribuir(){
        Vehiculo vehiculo;
        if(!Colas_Vehiculos_Clasificados.especiales.isEmpty()){
            vehiculo = Colas_Vehiculos_Clasificados.especiales.poll();
        }
        else{
            vehiculo = Colas_Vehiculos_Clasificados.normales.poll();
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
