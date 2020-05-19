/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import Entidades.Enums.PreciosDeVehiculos;
import java.util.LinkedList;
import Logger.Logger;
import java.util.logging.Level;

/**
 *
 * @author Teo
 */
public class Peaje extends Thread {

    private int cantCabinas;
    public static int totalDinero;
    public CabinaPeaje[] listaCabinas;
    private Reloj reloj;
    private boolean estado;
    //private int id_de_hilo;
    private final String CREACION = "Creacion de Peaje ";
    private final String CREACION_CABINAS = "Cabina creada y corriendo numero ";

    public Peaje(String nombre, int numCabinas, Reloj r) {
        //this.id_de_hilo = 1;
        this.totalDinero = 0;
        this.cantCabinas = numCabinas;
        this.estado = false;
        this.listaCabinas = new CabinaPeaje[cantCabinas];
        Logger.log(CREACION + nombre);
        for (int i = 0; i < numCabinas/2; i++) {
            listaCabinas[i] = new CabinaPeaje(i + 4, r, "este");//cambiar segun si esta el peaje o no
            listaCabinas[i].start();
        }
        for (int i = numCabinas/2; i < numCabinas; i++) {
            listaCabinas[i] = new CabinaPeaje(i + 4, r, "oeste");
            listaCabinas[i].start();
        }
    }

    @Override
    public void run() {
        while (Proyecto_peaje.cantidadSalida<Proyecto_peaje.cantidadEntrada) {
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
            try {
                //System.out.println("Ejecucion del ciclo del peaje que modifica las cabinas");//cambiar por sentencia para distrubucion de sentidos
                //reloj.hiloEjecutado(id_de_hilo);
                cambiarEstado();
            } catch (InterruptedException ex) {
                java.util.logging.Logger.getLogger(Peaje.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    public static synchronized void cobrar(int monto) {
        totalDinero += monto;
    }

    public void cambiarEstado() throws InterruptedException {
        if (estado == true) {
            estado = false;
        } else {
            estado = true;
        }
        sleep(1);
        synchronized (reloj) {
            if (reloj.chequearEstados()) {
                reloj.notifyAll();
            }
        }
    }
}
