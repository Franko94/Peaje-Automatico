/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades.Colas;

import Entidades.Reloj;
import Entidades.Vehiculo;
import Logger.Logger;
import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author apicos
 */
public class Colas_Vehiculos_Clasificados {

    private static Queue<Vehiculo> especialesEste = new LinkedList<>();
    private static Queue<Vehiculo> especialesOeste = new LinkedList<>();
    private static Queue<Vehiculo> normalesEste = new LinkedList<>();
    private static Queue<Vehiculo> normalesOeste = new LinkedList<>();

    public static void agregarVehiculo(Vehiculo v) {
        if (v.getDireccion().equalsIgnoreCase("este")) {
            if(v.isUnidad_especial()){
                especialesEste.add(v);
            }
            else{
                normalesEste.add(v);
            }
        } 
        else {
            if(v.isUnidad_especial()){
                especialesOeste.add(v);
            }
            else{
                normalesOeste.add(v);
            }
        }
    }

    public static Vehiculo getVehiculo(String sentido,Reloj r) {
        if (sentido.equalsIgnoreCase("este")) {
            Logger.log(r.getNumero_de_ciclo()+","+Thread.currentThread().getId()+","+
                "CabinaPeaje,llamarVehiculo,se fija si hay vehiculos especiales en direcion este");
            if(!especialesEste.isEmpty()){
                return especialesEste.poll();
            }
            else{
                return normalesEste.poll();
            }
        } else {
            Logger.log(r.getNumero_de_ciclo()+","+Thread.currentThread().getId()+","+
                "CabinaPeaje,llamarVehiculo,se fija si hay vehiculos especiales en direccion oeste");
            if(!especialesOeste.isEmpty()){
                return especialesOeste.poll();
            }
            else{
                return normalesOeste.poll();
            }
        }
    }
}
