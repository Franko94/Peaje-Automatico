/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author apicos
 */
public class Cola {

    Queue<Vehiculo> cola = new LinkedList<>();

    public void add_Vehiculo(Vehiculo v) {
        cola.add(v);
    }

    public Vehiculo obtener_primero() {//devuelve vehiculo o null
        return cola.poll();
    }
}
