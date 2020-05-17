/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import Entidades.Enums.PreciosDeVehiculos;
import java.util.LinkedList;

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

    public Peaje(String nombre, int numCabinas, Reloj r) {
        cantCabinas = numCabinas;

        for (int i = 0; i < numCabinas; i++) {
            listaCabinas[i] = new CabinaPeaje(i+1, r, "A");
            listaCabinas[i].start();
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
