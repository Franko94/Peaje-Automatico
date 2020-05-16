/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import Entidades.Enums.PreciosDeVehiculos;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 *
 * @author Teo
 */
public class Peaje {
    
    private String nombre;
    private int cantCabinas;
    public  ArrayList<CabinaPeaje> listaCabinas;
    private static int totalDinero = 0;
    
    public Peaje(String nombre, int numCabinas){
        this.nombre = nombre;
        cantCabinas = numCabinas;
        listaCabinas = new ArrayList<>();
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
    
    public void abrirCabinas(){
        for(int i = 0; i < cantCabinas; i++){
            listaCabinas.add(new CabinaPeaje(i));
        }
    }
    
}
