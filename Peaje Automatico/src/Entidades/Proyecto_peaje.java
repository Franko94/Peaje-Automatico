/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import Entidades.Cajas.Caja_de_vehiculos;
import Entidades.Cajas.Caja_de_finalizados;

/**
 *
 * @author Agustín Picos
 */
public class Proyecto_peaje {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Vehiculo v = new Vehiculo("matricula,tipo,true,false");
        Caja_de_vehiculos cajav = new Caja_de_vehiculos();
        cajav.cargar_vehiculos();
        
        Peaje peaje = new Peaje("Peaje Pando",9);
        peaje.abrirCabinas();
        peaje.abrir();
        System.out.println(peaje.listaCabinas.size());
        
        Caja_de_finalizados cajaf = new Caja_de_finalizados();
        Caja_de_finalizados.addVehiculo(v);
        cajaf.guardarAutosEnArchivo();
        System.out.println("");
        
        
        
        
        /**
         * Acá abajos vamos armado la ejecucion del programa final
         */
        
        /**
         * Primero: Creación de todos los objetos
         */
        
        /**
         * Segundo: Carga de datos
         */
        
        /**
         * Tercero: ejecucion del programa principal
         */
        
        /**
         * Cuarto: Guardado de datos
         */
    }

}
