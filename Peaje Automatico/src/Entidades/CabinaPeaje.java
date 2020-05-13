/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

/**
 *
 * @author fran_
 */
public class CabinaPeaje {
    
    private boolean habilitada;
    private int numero;
    private boolean ocupada;
    
    public CabinaPeaje(int numero){
        this.habilitada = true;
        this.numero = numero;
        this.ocupada = false;
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
}
