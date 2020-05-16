/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.util.Queue;

/**
 *
 * @author Teo
 */
public class PivotComunAEspecifica implements Runnable {
    
    public PivotComunAEspecifica(){

    }
     public void clasificarColas(){
        while (Cola_Comun_Ruta.cola.isEmpty()!= true){
            Vehiculo vehiculo = Cola_Comun_Ruta.cola.poll();
            if (vehiculo.isUnidad_especial()){
                Colas_Vehiculos_Clasificados.especiales.add(vehiculo);
            }
            else{
                Colas_Vehiculos_Clasificados.normales.add(vehiculo);
            }
        }
    }

    @Override
    public void run() {
        this.clasificarColas();
    }
}
