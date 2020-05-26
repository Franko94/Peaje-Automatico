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
public class Colas_Vehiculos_ManualesyAutomaticos {

    private static Queue<Vehiculo> telepeaje = new LinkedList<>();
    private static Queue<Vehiculo> manual = new LinkedList<>();

    public synchronized static void agregarVehiculo(Vehiculo v) {
        if (v.isTag()) {
            manual.add(v);
        } else {
            manual.add(v);//cambiar para sentidos
        }
    }
    public synchronized static Vehiculo getVehiculo(String sentido) {
            return manual.poll();
    }
    public synchronized static boolean estaVacia(String sentido) {
        return manual.isEmpty();
    }
}
