/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Teo
 */
/**
 * Dado que los hilos están sincronizados, el retraso por vehiculos especiales
 * se puede implementar aquí
 */
public class PivotComunAEspecifica implements Runnable {

    private int retraso_por_vehiculos_especiales;
/**
 * 
 * @param retraso 
 * 
 * 20 ms equivale a 30 segundos de la vida real
 */
    public PivotComunAEspecifica(int retraso) {
        this.retraso_por_vehiculos_especiales = retraso;

    }

    public void clasificarColas() {
        while (true) {
            Vehiculo vehiculo = Cola_Comun_Ruta.cola.poll();
            if(vehiculo==null){
                break;
            }
            if (vehiculo.isUnidad_especial()) {
                try {
                    Thread.sleep(retraso_por_vehiculos_especiales);
                } catch (InterruptedException ex) {
                    Logger.getLogger(PivotComunAEspecifica.class.getName()).log(Level.SEVERE, null, ex);
                }
                Colas_Vehiculos_Clasificados.especiales.add(vehiculo);
            } else {
                Colas_Vehiculos_Clasificados.normales.add(vehiculo);
            }
        }
    }

    @Override
    public void run() {
        this.clasificarColas();
    }
}
