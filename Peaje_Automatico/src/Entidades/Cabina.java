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
    private boolean estado = false;
    private int contador = 0;
    public Queue<Vehiculo> ee;
    public Queue<Vehiculo> eo;
    public Queue<Vehiculo> ne;
    public Queue<Vehiculo> no;

    /**
     *
     * @param idHilo
     */
    public Cabina(int idHilo, Reloj r, String dir,Queue<Vehiculo> a, Queue<Vehiculo> b,Queue<Vehiculo> c,Queue<Vehiculo> d) {
        super();
        this.reloj = r;
        this.direccion = dir;
        this.id_de_hilo = idHilo;
        this.ee=a;
        this.eo=b;
        this.ne=c;
        this.no=d;

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
                    e.printStackTrace();
                }
            }
            Vehiculo vehiculo = null;
            if (direccion.equalsIgnoreCase("este")) {
//                if (!Colas_Vehiculos_Clasificados.getemptyvee()) {
if(!ee.isEmpty()){
//                    vehiculo = Colas_Vehiculos_Clasificados.getvee();
                    vehiculo = ee.poll();
                } else {
                    System.out.println("cola especiales este vacia");
                    //if (!Colas_Vehiculos_Clasificados.getemptyvne()) {
                        if(!ne.isEmpty()){
                            vehiculo = ne.poll();
//                        vehiculo = Colas_Vehiculos_Clasificados.getvne();
                    } else {
                        System.out.println("cola normales este vacia");
                    }
                }
            } else {
//                if (!Colas_Vehiculos_Clasificados.getemptyveo()) {
                    if(!eo.isEmpty()){
//                    vehiculo = Colas_Vehiculos_Clasificados.getveo();
                    vehiculo = eo.poll();
                } else {
                    System.out.println("cola especiales oeste vacia");
//                    if (!Colas_Vehiculos_Clasificados.getemptyvno()) {
                        if(!no.isEmpty()){
                            vehiculo = no.poll();
//                        vehiculo = Colas_Vehiculos_Clasificados.getvno();
                    } else {
                        System.out.println("cola normales este vacia");
                    }
                }
            }
            if (vehiculo != null) {
                contador++;
                System.out.println(contador);
                //System.out.println(vehiculo.pasar_a_String());
//                Logger.log(reloj.getNumero_de_ciclo() + ","
//                        + Thread.currentThread().getId() + ","
//                        + "PivotComunAEspecifica,run, "
//                        + "El vehiculo especial de matricula: "
//                        + vehiculo.getMatricula() + " se posiciona en la"
//                        + " cola de vehiculos," + vehiculo.getDireccion());
            } else {
                //System.out.println("nulll");
                // contador++;
                //System.out.println(contador);
            }
            reloj.hiloEjecutado(id_de_hilo);
            //try {
                cambiarEstado();
            //} catch (InterruptedException ex) {
            //    java.util.logging.Logger.getLogger(PivotComunAEspecifica.class.getName()).log(Level.SEVERE, null, ex);
            //}
        }
    }

    public void cambiarEstado() {
        if (estado == true) {
            estado = false;
        } else {
            estado = true;
        }
        //sleep(1);
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
            java.util.logging.Logger.getLogger(CabinaPeaje.class.getName()).log(Level.SEVERE, null, ex);
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
}
