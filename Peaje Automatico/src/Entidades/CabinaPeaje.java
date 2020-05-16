/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author fran_
 */
public class CabinaPeaje extends Thread{
    
    private boolean habilitada;
    private int numero;
    private boolean ocupada;
    private Vehiculo vehiculo;
    
    public CabinaPeaje(int numero){
        this.habilitada = true;
        this.numero = numero;
        this.ocupada = false;
        vehiculo = null;
    }
    
    @Override
    public void run(){
        try {
            cobrarVehiculo();
            wait(); //con esto no volvera a tomar accion hasta que ocurra un notify
                    //externo
        } catch (InterruptedException ex) {
            Logger.getLogger(CabinaPeaje.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void cobrarVehiculo(){
        if(vehiculo != null){
            Peaje.cobrar(vehiculo);
            vehiculo = null;
            setOcupada(false);
        }
    }

    public int getNumero() {
        return numero;
    }
    
    public boolean getHabilitada(){
        return habilitada;
    }
    
    public boolean getOcupada(){
        return ocupada;
    }
    
    public void setHabilitada(boolean habilitada){
        this.habilitada = habilitada;
    }
    
    public void setOcupada(boolean ocupada){
        this.ocupada = ocupada;
    }
    
    public void setVehiculo(Vehiculo vehiculo){
        this.vehiculo = vehiculo;
    }
}
