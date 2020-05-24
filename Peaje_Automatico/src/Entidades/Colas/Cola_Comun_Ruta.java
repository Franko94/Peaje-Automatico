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

    private static Queue<Vehiculo> colaEste = new LinkedList<>();
    private static Queue<Vehiculo> colaOeste = new LinkedList<>();

    public synchronized static void agregarVehiculo(Vehiculo v) {
        if (v.getDireccion().equalsIgnoreCase("este")) {
            colaEste.add(v);
        } else {
            colaEste.add(v);//cambiar para dos 
        }
    }

    public synchronized static Vehiculo getVehiculo(String sentido) {
        synchronized (Cola_Comun_Ruta.colaEste) {
            if (sentido.equalsIgnoreCase("este")) {
                return colaEste.poll();
            } 
            
        }
        return null;
    }

    public synchronized static boolean estaVacia(String sentido) {
        if (sentido.equalsIgnoreCase("este")) {
            return colaEste.isEmpty();
        } else {
            return colaOeste.isEmpty();
        }
    }
}
/**
 * hay que ver si los metodos los podemos acceder desde objetos distintos
 * en casa afirmativo, creamos dos carriles o colas por ruta
 * en caso negativo, hay que crear dos clases de ruta.
 * 
 * hay que hacer la cola de los manuales/automaticos
 */
