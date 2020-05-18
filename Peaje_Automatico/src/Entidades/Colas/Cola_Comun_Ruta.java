/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades.Colas;

import Entidades.Vehiculo;
import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author apicos
 */
public class Cola_Comun_Ruta {

    private static Queue <Vehiculo> colaEste=new LinkedList<>();
    private static Queue <Vehiculo> colaOeste=new LinkedList<>();
    
    public static void agregarVehiculo(Vehiculo v) {
        if (v.getDireccion().equalsIgnoreCase("este")) {
            colaEste.add(v);
        } else {
            colaOeste.add(v);
        }
    }
    public static Vehiculo getVehiculo(String sentido) {
        if (sentido.equalsIgnoreCase("este")) {
            return colaEste.poll();
        } else {
            return colaOeste.poll();
        }
    }
    public static boolean estaVacia(String sentido){
        if (sentido.equalsIgnoreCase("este")) {
            return colaEste.isEmpty();
        } else {
            return colaOeste.isEmpty();
        }
    }
}
