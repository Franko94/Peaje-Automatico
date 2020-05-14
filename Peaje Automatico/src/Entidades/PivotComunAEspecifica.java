/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.util.Queue;

/**
 *
 * @author Teo
 */
public class PivotComunAEspecifica {
    
    public static Queue <Vehiculo> colaComun;
    public static Queue <Vehiculo> colaNormales;
    public static Queue <Vehiculo> colaEspeciales;
    
    public PivotComunAEspecifica(Queue colaComun, Queue colaNormales, Queue colaEspeciales){
        this.colaComun = colaComun;
        this.colaNormales = colaNormales;
        this.colaEspeciales = colaEspeciales;
    }
    
    public void clasificarColas(){
        while (colaComun.isEmpty() != true){
            Vehiculo vehiculo = colaComun.poll();
            if (vehiculo.isUnidad_especial()){
                colaEspeciales.add(vehiculo);
            }
            else{
                colaNormales.add(vehiculo);
            }
        }
    }
}
