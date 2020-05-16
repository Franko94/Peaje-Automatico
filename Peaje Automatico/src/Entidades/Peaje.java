/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.util.LinkedList;
//import peaje.automatico.Entidades.Vehiculo;

/**
 *
 * @author Teo
 */
public class Peaje {
    
    private String nombre;
    private int cantCabinas;
    LinkedList listaCabinas;
    private int totalDinero = 0;
    
    
    public void Peaje(String nombre, int numCabinas){
        this.nombre = nombre;
        cantCabinas = numCabinas;
        listaCabinas = new LinkedList();
    }
    
    
    public void cobrar(Vehiculo vehiculo){
        totalDinero += this.fijarsePrecio(vehiculo);
        vehiculo.setHoraSalida(0);
    }
    
    public int fijarsePrecio(Vehiculo vehiculo){
        int x = -1;
        if (vehiculo.getTipo() == "auto"){
            x=10;
        }
        else if (vehiculo.getTipo() == "camion"){
            x=20;
        }
        else if (vehiculo.getTipo() == "moto"){
            x=15;
        }
        return x;
    }
    
}
