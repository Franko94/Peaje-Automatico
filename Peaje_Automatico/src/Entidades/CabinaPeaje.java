/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import Entidades.Cajas.Caja_de_finalizados;
import Entidades.Colas.Colas_Vehiculos_Clasificados;
import Entidades.Enums.PreciosDeVehiculos;
import Logger.Logger;

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
    private String sentido;

    public CabinaPeaje(int numero, Reloj r, String s) {
        super();
        this.reloj = r;
        this.habilitada = true;
        this.numero = numero;
        this.vehiculo = null;
        this.sentido = s;//esto es para indicar de qué caja toma los autos
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
            if (this.habilitada == true) {
                llamarVehiculo();
                cobrarVehiculo();
            }
            reloj.hiloEjecutado(numero);
            cambiarEstado();
        }
    }

    private synchronized void llamarVehiculo() {
        Logger.log(reloj.getNumero_de_ciclo()+","+
                Thread.currentThread().getId()+","+
                "CabinaPeaje,llamarVehiculo, a la cabina "+numero);
        if (!Colas_Vehiculos_Clasificados.especiales.isEmpty()) {
            vehiculo = Colas_Vehiculos_Clasificados.especiales.poll();
            Logger.log(reloj.getNumero_de_ciclo()+","+
                Thread.currentThread().getId()+","+
                "CabinaPeaje,llamarVehiculo, Vehiculo accede a cabina numero "
                    + numero+" con matricula "+ 
                    vehiculo.getMatricula());
        } else {
            vehiculo = Colas_Vehiculos_Clasificados.normales.poll();
            Logger.log(reloj.getNumero_de_ciclo()+","+
                Thread.currentThread().getId()+","+
                "CabinaPeaje,llamarVehiculo"+" Cabina numero "+ numero 
                    + " no encontro vehiculo ");
             
        }
    }

    private void cobrarVehiculo() {
        if (vehiculo != null) {
             Logger.log(reloj.getNumero_de_ciclo()+","+
                Thread.currentThread().getId()+","+"CabinaPeaje,cobrarVehiculo,"+
            "Vehiculo "+ vehiculo.getMatricula() + "Cobrado por cabina numero" + numero);
            int monto = fijarsePrecio(vehiculo);
            Peaje.cobrar(monto);
            vehiculo.setHoraSalida(System.nanoTime());
            Caja_de_finalizados.addVehiculo(vehiculo);
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
