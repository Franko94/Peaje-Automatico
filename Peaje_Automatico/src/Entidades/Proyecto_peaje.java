/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import Entidades.Cajas.Caja_de_vehiculos;
import Entidades.Cajas.Caja_de_finalizados;
import Entidades.Cajas.Caja_de_Frecuencia;
import Entidades.Colas.Cola_Comun_Ruta;
import Entidades.Colas.Colas_Vehiculos_Clasificados;
import Entidades.Pivots.PivotComunAEspecifica;
import Logger.Logger;

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
        
        /*
        CSV
        Ciclo reloj;ihHilo;Clase;Metodo,descripcion
        
        */
        /**
         * Acá abajos vamos armado la ejecucion del programa final
         */
        /**
         * Primero: Creación de todos los objetos
         */
        final Reloj reloj = new Reloj();
        Caja_de_vehiculos caja_de_vehiculos = new Caja_de_vehiculos();
        Caja_de_Frecuencia caja_de_Frecuencia = new Caja_de_Frecuencia(0, reloj);
        PivotComunAEspecifica pivotComunAEspecifica = new PivotComunAEspecifica(0, reloj);
        Cola_Comun_Ruta cola_Comun_Ruta = new Cola_Comun_Ruta();
        Colas_Vehiculos_Clasificados colas_Vehiculos_Clasificados = new Colas_Vehiculos_Clasificados();
        Caja_de_finalizados caja_de_finalizados = new Caja_de_finalizados();
        /**
         * Segundo: Carga de datos
         */
        Caja_de_vehiculos.cargar_vehiculos();
        /**
         * Tercero: ejecucion del programa principal
         */
        caja_de_Frecuencia.start();
        pivotComunAEspecifica.start();
        Peaje peaje = new Peaje("Peaje Pando", 2, reloj);//se crea y tambien se ejecuta, cambiar cantidad de cabinas
        /**
         * Cuarto: Guardado de datos
         */
        try {

            Thread.sleep(10000);
        } catch (InterruptedException ex) {
            
        }
        Caja_de_finalizados.guardarAutosEnArchivo();
    }

}