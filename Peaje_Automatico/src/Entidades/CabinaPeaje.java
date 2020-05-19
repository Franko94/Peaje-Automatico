/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import Entidades.Colas.Colas_Vehiculos_Clasificados;
import Entidades.Enums.PreciosDeVehiculos;
import Logger.Logger;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;

/**
 *
 * @author fran_
 */
public class CabinaPeaje extends Thread {

    private Reloj reloj;
    private boolean estado = false;
    private boolean habilitada;
    private int numero;//comienzan en 1
    private boolean ocupada;
    private Vehiculo vehiculo;
    private String direccion;

    public CabinaPeaje(int numero, Reloj r, String dir) {
        super();
        this.reloj = r;
        this.habilitada = true;
        this.numero = numero;
        this.vehiculo = null;
        this.direccion = dir;//esto es para indicar de qué caja toma los autos
    }

    @Override
    public void run() {
        while (Proyecto_peaje.cantidadSalida<Proyecto_peaje.cantidadEntrada) {
            if (!reloj.nuevoCiclo(estado)) {
                try {
                    synchronized (reloj) {
                        reloj.wait();
                    }
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            if (this.habilitada) {
                llamarVehiculo();
                try {
                    cobrarVehiculo();
                } catch (InterruptedException ex) {
                    java.util.logging.Logger.getLogger(CabinaPeaje.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            reloj.hiloEjecutado(numero);
            try {
                cambiarEstado();
            } catch (InterruptedException ex) {
                java.util.logging.Logger.getLogger(CabinaPeaje.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private synchronized void llamarVehiculo() {
        vehiculo = Colas_Vehiculos_Clasificados.getVehiculo(this.direccion, this.reloj);
        if (vehiculo == null) {
            Logger.log(reloj.getNumero_de_ciclo() + ","
                    + Thread.currentThread().getId() + ","
                    + "CabinaPeaje,llamarVehiculo," + " Cabina numero " + numero
                    + " no encontro vehiculo en ninguna cola.");
        } else {
            Logger.log(reloj.getNumero_de_ciclo() + ","
                    + Thread.currentThread().getId() + ","
                    + "CabinaPeaje,llamarVehiculo," + " Vehiculo accede a cabina numero,"
                    + numero + ",con matricula,"
                    + vehiculo.getMatricula());
        }
    }

    private void cobrarVehiculo() throws InterruptedException {
        if (vehiculo != null) {
            Logger.log(reloj.getNumero_de_ciclo() + ","
                    + Thread.currentThread().getId() + "," + "CabinaPeaje,cobrarVehiculo,"
                    + "Vehiculo," + vehiculo.getMatricula() + ",Cobrado por cabina numero," 
                    + numero + ",viajando hacia," + this.direccion);
            int monto = fijarsePrecio(vehiculo);
            Peaje.cobrar(monto);
            vehiculo.setHoraSalida(System.nanoTime());
            guardarAutosEnArchivo(vehiculo);
            Proyecto_peaje.addSalida();
            vehiculo = null;
        }
    }

    public int getNumero() {
        return numero;
    }

    public boolean getHabilitada() {
        return habilitada;
    }

    public boolean getOcupada() {
        return ocupada;
    }

    public void setHabilitada(boolean habilitada) {
        this.habilitada = habilitada;
    }

    private static int fijarsePrecio(Vehiculo vehiculo) {
        String tipoDelVehiculo = vehiculo.getTipo();
        int respuesta = PreciosDeVehiculos.valueOf(tipoDelVehiculo).label;
        return respuesta;
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
