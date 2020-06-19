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

    private static Queue<Vehiculo> telepeajeEste = new LinkedList<>();
    private static Queue<Vehiculo> manualEste = new LinkedList<>();
    private static Queue<Vehiculo> telepeajeOeste = new LinkedList<>();
    private static Queue<Vehiculo> manualOeste = new LinkedList<>();

    public synchronized static void agregarVehiculo(Vehiculo v) {
        if (v.isTag()) {
            if (v.getDireccion().equalsIgnoreCase("este")) {
                telepeajeEste.add(v);
            } else {
                telepeajeOeste.add(v);
            }
        } else {
            if (v.getDireccion().equalsIgnoreCase("este")) {
                manualEste.add(v);
            } else {
                manualOeste.add(v);
            }
        }
    }

    public synchronized static Vehiculo getManual(String sentido) {
        Vehiculo v = null;
        if (sentido.equalsIgnoreCase("este")) {
            v = manualEste.poll();
        } else {
            v = manualOeste.poll();
        }
        return v;
    }

    public synchronized static Vehiculo getTelepeaje(String sentido) {
        Vehiculo v = null;
        if (sentido.equalsIgnoreCase("este")) {
            v=telepeajeEste.poll();
        } else {
            v=telepeajeOeste.poll();
        }
        return v;
    }
}
