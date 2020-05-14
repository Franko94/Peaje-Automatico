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
    TipoDeVehiculos tipos;
    
    public void Peaje(String nombre, int numCabinas){
        this.nombre = nombre;
        cantCabinas = numCabinas;
        listaCabinas = new LinkedList();
    }
    
    
    public void cobrar(Vehiculo vehiculo){
        totalDinero += this.fijarsePrecio(vehiculo);
        vehiculo.setHoraFin(0);
    }
    
    public int fijarsePrecio(Vehiculo vehiculo){
        String tipoDelVehiculo = vehiculo.getTipo();
        return tipos.valueOf(tipoDelVehiculo).label;
    }
    
}
