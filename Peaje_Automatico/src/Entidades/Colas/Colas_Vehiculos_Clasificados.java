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
//    private static Queue<Vehiculo> especialesEste = new LinkedList<>();
//    private static Queue<Vehiculo> especialesOeste = new LinkedList<>();

    private static Queue<Vehiculo> normalesEste = new LinkedList<>();
    private static Queue<Vehiculo> normalesOeste = new LinkedList<>();

    public synchronized static void agregarVehiculo(Vehiculo v) {
        if (v.getDireccion().equalsIgnoreCase("este")) {
            normalesEste.add(v);
        } else {
            normalesEste.add(v);//cambiar para sentidos
        }
    }

    public synchronized static Vehiculo getVehiculo(String sentido) {
        synchronized (Colas_Vehiculos_Clasificados.normalesEste) {
//            if (sentido.equalsIgnoreCase("este")) {
            return normalesEste.poll();
//            } 

        }
    }

    public synchronized static boolean estaVacia(String sentido) {
        return normalesEste.isEmpty();
    }
}
