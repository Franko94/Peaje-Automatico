/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

/**
 *
 * @author Usuario
 */
public class HabilitadorDeCabinas extends Thread {

    private final Reloj reloj;
    private final int id_de_hilo;
    private boolean estado;
    private int contadorc = -1;
    private int contadort = -1;

    public HabilitadorDeCabinas(Reloj r, int idHilo) {
        super();
        this.reloj = r;
        this.id_de_hilo = idHilo;
        this.estado = false;
    }

    @Override
    public void run() {
        while (Proyecto_peaje.cantidadEntrada > Proyecto_peaje.cantidadSalida) {
            if (reloj.nuevoCiclo(id_de_hilo, "habilitador de cabinas") != true) {
                try {
                    synchronized (reloj) {
                        reloj.wait(5);
                    }
                } catch (InterruptedException e) {
                }
            }
            if (contadorc <= 0) {
                contadorc = 600;
                for (Cabina cabina : Peaje.getListaCabinas()) {
                    if (!cabina.getHabilitada()) {
                        cabina.setHabilitada(true);
                    }
                }
            } else {
                contadorc--;
            }
            if (contadort <= 0) {
                contadort =350;
                for (Telepeaje telepeaje : Peaje.getListaTelepeaje()) {
                    if (!telepeaje.getHabilitada()) {
                        telepeaje.setHabilitada(true);
                    }
                }
            } else {
                contadort--;
            }
            reloj.hiloEjecutado("Habilitador de cabinas", id_de_hilo);
        }
    }
}
