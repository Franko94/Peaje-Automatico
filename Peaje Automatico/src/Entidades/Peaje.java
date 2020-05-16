/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import Entidades.Enums.PreciosDeVehiculos;
import java.util.LinkedList;

/**
 *
 * @author Teo
 */
public class Peaje {
    
    private String nombre;
    private int cantCabinas;
    LinkedList listaCabinas;
    private static int totalDinero = 0;
    
    public void Peaje(String nombre, int numCabinas){
        this.nombre = nombre;
        cantCabinas = numCabinas;
        listaCabinas = new LinkedList();
    }
    

    public static synchronized void cobrar(Vehiculo vehiculo){
        setTotalDinero(fijarsePrecio(vehiculo));
        vehiculo.setHoraSalida(0);
    }
    
    private static int fijarsePrecio(Vehiculo vehiculo){
        String tipoDelVehiculo = vehiculo.getTipo();
        return PreciosDeVehiculos.valueOf(tipoDelVehiculo).label;
    }
    
    private static void setTotalDinero(int monto){
        totalDinero += monto;

    }
    
}
