/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import Entidades.Enums.PreciosDeVehiculos;
import java.util.LinkedList;
import Logger.Logger;
import java.util.ArrayList;
import java.util.logging.Level;

/**
 *
 * @author Teo
 */
public class Peaje extends Thread {

    private int cantCabinas;
    public static int totalDinero;
    public static ArrayList<Cabina> listaCabinas = new ArrayList<>();
    private final Reloj reloj;
    private boolean estado;
    private int id_de_hilo;
    private final String CREACION = "Creacion de Peaje ";
    private final String CREACION_CABINAS = "Cabina creada y corriendo numero ";

    public Peaje(String nombre, int numCabinas, Reloj r) {
        this.reloj = r;
    }

    public static ArrayList<Cabina> getListaCabinas() {
        return listaCabinas;
    }

    @Override
    public void run() {
        while (true) {
            if (reloj.nuevoCiclo(id_de_hilo) != true) {
                try {
                    synchronized (reloj) {
                        reloj.wait();
                    }
                } catch (InterruptedException e) {
                    System.out.println("InterruptedException");
                }
            }
        }
    }

    public static synchronized void cobrar(int monto) {
        totalDinero += monto;
    }
}
