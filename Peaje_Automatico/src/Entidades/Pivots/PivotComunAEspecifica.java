/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades.Pivots;
import Entidades.Reloj;
import Entidades.Colas.Cola_Comun_Ruta;
import Entidades.Colas.Colas_Vehiculos_ManualesyAutomaticos;
import Entidades.Proyecto_peaje;
import Entidades.Vehiculo;
import Logger.Logger;
import java.time.LocalDateTime;

/**
 *
 * @author Teo
 */
public class PivotComunAEspecifica extends Thread {

    private int retraso_por_vehiculos_especiales;
    private Reloj reloj;
    private boolean estado = false;
    private int id_de_hilo;
    private String direccion;

    public PivotComunAEspecifica(int retraso, Reloj r, int idHilo, String dir) {
        super();
        this.reloj = r;
        this.retraso_por_vehiculos_especiales = retraso;
        this.direccion = dir;
        this.id_de_hilo = idHilo;
    }

    @Override
    public void run() {
        while (Proyecto_peaje.cantidadEntrada > Proyecto_peaje.cantidadSalida) {
            if (reloj.nuevoCiclo(id_de_hilo,"pivot") != true) {
                try {
                    synchronized (reloj) {
                        reloj.wait(5);
                    }
                } catch (InterruptedException e) {
                }
            }
            Vehiculo v = Cola_Comun_Ruta.getVehiculo(direccion);
            if (v != null) {
                Colas_Vehiculos_ManualesyAutomaticos.agregarVehiculo(v);
                Logger.agregarLog(reloj.getNumero_de_ciclo() + ","+"Pivot,"
                        + id_de_hilo + ","
                        + "PivotComunAEspecifica,run, "
                        + "El vehiculo especial de matricula: "
                        + v.getMatricula() + " se posiciona en la"
                        + " cola de vehiculos " + v.getDireccion() + "," + LocalDateTime.now());
            }
            reloj.hiloEjecutado("Pivot",id_de_hilo);
        }
    }
}
