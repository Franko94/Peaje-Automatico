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
    private Queue <Vehiculo> cola_para_despachar;
    
    public Caja_de_Frecuencia(int autos_per_minute,Queue<Vehiculo> cola){
        this.autos_por_minuto=autos_per_minute;
        this.cola_para_despachar = cola;
    }
    @Override
    public void run() {
        while (!cola_para_despachar.isEmpty()) {
            int dormir= 
            Vehiculo v = cola_para_despachar.poll();
            if(v.isUnidad_especial()){
                Cola_Vehiculos_Especiales.cola.add(v);
            }
            else{
                Cola_Comun_Ruta.cola.add(v);
            }
        }
    }
    
}
