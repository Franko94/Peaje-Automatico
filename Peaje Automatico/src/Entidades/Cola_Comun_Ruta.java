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
public class Cola_Comun_Ruta {

    public static Queue <Vehiculo> cola;

    public static void add_Vehiculo(Vehiculo v) {
        cola.add(v);
    }
    public static Vehiculo getPrimero(){
        return cola.poll();
    }
}
