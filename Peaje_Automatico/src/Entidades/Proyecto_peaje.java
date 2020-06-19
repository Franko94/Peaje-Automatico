/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import Entidades.Cajas.Caja_de_vehiculos;
import Entidades.Cajas.Caja_de_Frecuencia;
import Entidades.Colas.Cola_Comun_Ruta;
import Entidades.Colas.Colas_Vehiculos_ManualesyAutomaticos;
import Entidades.Pivots.PivotComunAEspecifica;
import Logger.Logger;
import java.util.Set;

/**
 *
 * @author Agustín Picos
 */
public class Proyecto_peaje {

    /**
     * @param args the command line arguments
     */
    public static int cantidadEntrada = 0;
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
        Logger logger = new Logger();
        final Reloj reloj = new Reloj();
        
        Caja_de_vehiculos caja_de_vehiculos = new Caja_de_vehiculos();
        
        Caja_de_Frecuencia caja_de_Frecuencia_Este = new Caja_de_Frecuencia(0, reloj, 0, "este");
        Caja_de_Frecuencia caja_de_Frecuencia_Oeste = new Caja_de_Frecuencia(0, reloj, 1, "oeste");
        
        Cola_Comun_Ruta cola_Comun_Ruta = new Cola_Comun_Ruta();
        
        Colas_Vehiculos_ManualesyAutomaticos colas_Vehiculos_Clasificados = new Colas_Vehiculos_ManualesyAutomaticos();
        
        PivotComunAEspecifica pivotComunAEspecificaEste = new PivotComunAEspecifica(0, reloj, 2, "este");
        PivotComunAEspecifica pivotComunAEspecificaOeste = new PivotComunAEspecifica(0, reloj, 8, "oeste");
        
        Telepeaje telepeajeEste = new Telepeaje(9, reloj, "este");
        Telepeaje telepeajeOeste = new Telepeaje(10, reloj, "oeste");
        Telepeaje telepeajeEste2 = new Telepeaje(11, reloj, "este");
        Telepeaje telepeajeOeste2 = new Telepeaje(12, reloj, "oeste");
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
        pivotComunAEspecificaOeste.start();
        telepeajeEste.start();
        telepeajeOeste.start();
        telepeajeEste2.start();
        telepeajeOeste2.start();
        
        /**
         * prueba cabinas afuera
         */
        Cabina cabina1= new Cabina(3, reloj, "este");
        Cabina cabina2= new Cabina(5, reloj, "oeste");
        Cabina cabina3= new Cabina(6, reloj, "este");
        Cabina cabina4= new Cabina(7, reloj, "oeste");
        Peaje.listaCabinas.add(cabina1);
        cabina1.start();
        cabina2.start();
        cabina3.start();
        cabina4.start();
        HabilitadorDeCabinas habilitadorDeCabinas = new HabilitadorDeCabinas(reloj, 4);
        habilitadorDeCabinas.start();       
        
        /**
         * Cuarto: Guardado de datos
         * Al completarse el pasaje de todos los vehiculos, el logger guarda 
         * la salida y los logs en un archivo cada uno
         */
    }

}
/**
 * crear la clase camaras/antenas/tag
 * va a trabajar como la cabina, los metodos varian un poco
 * sacar foto de la matricula
 * cobrar por tag, ver si tiene saldo
 * si tiene tag se cobra
 * si tiene tag sin saldo o si no tiene tag, hay que ver la foto y reportarla
 * pasarlo a una lista.
 */

/**
 * pendiente:
 * hay que manejar los accidentes y la redistribucion de los carriles
 */
