/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import Entidades.Colas.Cola_Comun_Ruta;
import Entidades.Colas.Colas_Vehiculos_Clasificados;
import Entidades.Pivots.PivotComunAEspecifica;
import Logger.Logger;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import static java.lang.Thread.sleep;
import java.util.Queue;
import java.util.logging.Level;

/**
 *
 * @author apicos
 */
public class Cabina extends Thread {

    private Reloj reloj;
    private boolean habilitada = false;
    private int id_de_hilo;
    private String direccion;
    private boolean estado;
    private int contador = 0;

    public Cabina(int idHilo, Reloj r, String dir) {
        super();
        this.reloj = r;
        this.direccion = dir;
        this.id_de_hilo = idHilo;
        this.estado = false;
    }

    @Override
    public void run() {
        while (true) {
            Vehiculo v = null;
            if (reloj.nuevoCiclo(estado) != true) {
                try {
                    synchronized (reloj) {
                        reloj.wait();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            v = getVehiculo(direccion);
            if (v != null) {
                System.out.println(v.pasar_a_String());
                guardarAutosEnArchivo(v);
            }
            reloj.hiloEjecutado(id_de_hilo);
            try {
                cambiarEstado();
            } catch (InterruptedException ex) {
                java.util.logging.Logger.getLogger(Cabina.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void cambiarEstado() throws InterruptedException {
        if (estado == true) {
            estado = false;
        } else {
            estado = true;
        }
        synchronized (reloj) {
            if (reloj.chequearEstados() == true) {
                reloj.notifyAll();
            }
        }
    }

    public static synchronized void guardarAutosEnArchivo(Vehiculo v) {
        FileWriter fw;
        try {
            sleep(1);
        } catch (InterruptedException ex) {
        }
        try {
            fw = new FileWriter("src\\Escenarios\\archivo_salida_vehiculos.txt", true);
            BufferedWriter bw = new BufferedWriter(fw);
            String lineaActual = v.pasar_a_String();
            bw.write(lineaActual);
            bw.newLine();
            bw.close();
            fw.close();
        } catch (IOException e) {
            System.out.println("Error al escribir el archivo "
                    + "src\\Escenarios\\archivo_salida_vehiculos.txt");
            e.printStackTrace();
        }
    }

    public synchronized Vehiculo getVehiculo(String dir) {
        Vehiculo v;
        v = Colas_Vehiculos_Clasificados.getVehiculo(direccion);
        return v;
    }
}
