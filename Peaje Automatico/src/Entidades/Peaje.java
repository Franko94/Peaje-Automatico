/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import Entidades.Enums.PreciosDeVehiculos;
import java.util.LinkedList;
import Logger.Logger;
/**
 *
 * @author Teo
 */
public class Peaje extends Thread {

    private int cantCabinas;
    private static int totalDinero = 0;
    public CabinaPeaje[] listaCabinas = new CabinaPeaje[cantCabinas];
    private Reloj reloj;
    private boolean estado = false;
    private int id_de_hilo = 1;
    
    private final String CREACION = "Creacion de Peaje ";
    private final String CREACION_CABINAS = "Cabina creada y corriendo numero ";
    
    public Peaje(String nombre, int numCabinas, Reloj r) {
        cantCabinas = numCabinas;
        Logger.log(CREACION + nombre);
        for (int i = 0; i < numCabinas; i++) {
            listaCabinas[i] = new CabinaPeaje(i+1, r, "A");
            listaCabinas[i].start();
            Logger.log(CREACION_CABINAS);
        }
    }
    @Override
    public void run() {
        while (true) {
            if (reloj.nuevoCiclo(estado) != true) {
                try {
                    synchronized (reloj) {
                        reloj.wait();
                    }
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            reloj.hiloEjecutado(id_de_hilo);
            cambiarEstado();
            System.out.println("Ejecucion del ciclo del peaje que modifica las cabinas");//cambiar por sentencia para distrubucion de sentidos
            
        }
    }

    public static synchronized void cobrar(int monto) {
        totalDinero += monto;
    }

    public void cambiarEstado() {
        if (estado == true) {
            estado = false;
        } else {
            estado = true;
        }
        synchronized (reloj) {
            if (reloj.chequearEstados()) {
                reloj.notifyAll();
            }
        }
    }
}
