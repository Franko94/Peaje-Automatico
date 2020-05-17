/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import Entidades.Cajas.Caja_de_finalizados;
import Entidades.Colas.Colas_Vehiculos_Clasificados;
import Entidades.Enums.PreciosDeVehiculos;

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
            reloj.hiloEjecutado(numero+1);
            cambiarEstado();
            if (this.habilitada == true) {
                llamarVehiculo();
                cobrarVehiculo();
            }
        }
    }

    private synchronized void llamarVehiculo() {
        if (!Colas_Vehiculos_Clasificados.especiales.isEmpty()) {
            vehiculo = Colas_Vehiculos_Clasificados.especiales.poll();
        } else {
            vehiculo = Colas_Vehiculos_Clasificados.normales.poll();
        }
    }

    private void cobrarVehiculo() {
        if (vehiculo != null) {
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
        return PreciosDeVehiculos.valueOf(tipoDelVehiculo).label;
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
