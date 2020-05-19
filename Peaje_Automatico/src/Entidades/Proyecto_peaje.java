/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import Entidades.Cajas.Caja_de_vehiculos;
import Entidades.Cajas.Caja_de_Frecuencia;
import Entidades.Colas.Cola_Comun_Ruta;
import Entidades.Colas.Colas_Vehiculos_Clasificados;
import Entidades.Pivots.PivotComunAEspecifica;
import Logger.Logger;
import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author Agustín Picos
 */
public class Proyecto_peaje {

    /**
     * @param args the command line arguments
     */
    public static int cantidadEntrada = 8640;
    public static int cantidadSalida = 0;
    

    public static void main(String[] args) throws InterruptedException {
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
        Caja_de_Frecuencia caja_de_Frecuencia_Este = new Caja_de_Frecuencia(0, reloj, 0, "este");
        Caja_de_Frecuencia caja_de_Frecuencia_Oeste = new Caja_de_Frecuencia(0, reloj, 1, "oeste");
        Cola_Comun_Ruta cola_Comun_Ruta = new Cola_Comun_Ruta();
        Colas_Vehiculos_Clasificados colas_Vehiculos_Clasificados = new Colas_Vehiculos_Clasificados();
        
        PivotComunAEspecifica pivotComunAEspecificaEste = new PivotComunAEspecifica(0, reloj, 2, "este");
        /**
         * Segundo: Carga de datos
         */
        Caja_de_vehiculos.cargar_vehiculos();
        /**
         * Tercero: ejecucion del programa principal
         */
        caja_de_Frecuencia_Este.start();
        caja_de_Frecuencia_Oeste.start();
        pivotComunAEspecificaEste.start();
        /**
         * prueba cabinas afuera
         */
        Cabina cabina1= new Cabina(3, reloj, "este");
        
        cabina1.start();
        /**
         * Cuarto: Guardado de datos
         */
    }

}
