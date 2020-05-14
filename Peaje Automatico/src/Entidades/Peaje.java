/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.util.LinkedList;


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
        vehiculo.setHoraFin(0);
    }
    
    enum tiposDeVehiculos{
        auto (20),
        moto (10),
        camion (30),
        omnibus (25);
        
        public final int label;
        
        private tiposDeVehiculos(int label){
            this.label = label;
        }
    }
    
    public int fijarsePrecio(Vehiculo vehiculo){
        String tipoDelVehiculo = vehiculo.getTipo();
        return tiposDeVehiculos.valueOf(tipoDelVehiculo).label;
    }
    
}
