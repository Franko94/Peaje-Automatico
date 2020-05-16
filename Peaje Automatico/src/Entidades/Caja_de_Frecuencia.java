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
    
    public Caja_de_Frecuencia(int autos_per_minute){
        this.autos_por_minuto=autos_per_minute;
    }
    @Override
    public void run() {
        //int dormir = x;
        while (Caja_de_vehiculos.cola.isEmpty()!=true) {
            Vehiculo v = Caja_de_vehiculos.cola.poll();
            Cola_Comun_Ruta.cola.add(v);
        }
    }
    
}
