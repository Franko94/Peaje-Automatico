/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.util.Queue;

/**
 *
 * @author apicos
 */
public class Caja_de_Frecuencia implements Runnable{
    private int autos_por_minuto;
    private Caja_de_vehiculos caja_de_vehiculos;
    
    public Caja_de_Frecuencia(int autos_per_minute,Caja_de_vehiculos caja_vehiculos){
        this.autos_por_minuto=autos_per_minute;
        this.caja_de_vehiculos=caja_vehiculos;
    }
    @Override
    public void run() {
        //int dormir = x;
        while (caja_de_vehiculos.getPrimero()!= null) {
            Vehiculo v = caja_de_vehiculos.getPrimero();
            Cola_Comun_Ruta.add_Vehiculo(v);
        }
    }
    
}
