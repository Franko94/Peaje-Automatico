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

/**
 *
 * @author Agustín Picos
 */
public class Proyecto_peaje {
    public static int cantidadEntrada = 0;
    public static int cantidadSalida = 0;

    public static void main(String[] args) throws InterruptedException {
        /**
         * Primero: Creación de todos los objetos
         */
        Logger logger = new Logger();
        final Reloj reloj = new Reloj();

        Caja_de_vehiculos caja_de_vehiculos = new Caja_de_vehiculos();

        Caja_de_Frecuencia caja_de_Frecuencia_Este = new Caja_de_Frecuencia(5/*autos por minuto*/, reloj, 0, "este");
        Caja_de_Frecuencia caja_de_Frecuencia_Oeste = new Caja_de_Frecuencia(5, reloj, 1, "oeste");

        Cola_Comun_Ruta cola_Comun_Ruta = new Cola_Comun_Ruta();

        Colas_Vehiculos_ManualesyAutomaticos colas_Vehiculos_Clasificados = new Colas_Vehiculos_ManualesyAutomaticos();

        PivotComunAEspecifica pivotComunAEspecificaEste = new PivotComunAEspecifica(0, reloj, 2, "este");
        PivotComunAEspecifica pivotComunAEspecificaOeste = new PivotComunAEspecifica(0, reloj, 8, "oeste");

        Telepeaje telepeajeEste = new Telepeaje(9, reloj, "este");
        Telepeaje telepeajeOeste = new Telepeaje(10, reloj, "oeste");
        Telepeaje telepeajeEste2 = new Telepeaje(11, reloj, "este");
        Telepeaje telepeajeOeste2 = new Telepeaje(12, reloj, "oeste");

        HabilitadorDeCabinas habilitadorDeCabinas = new HabilitadorDeCabinas(reloj, 4);

        /**
         *
         * Segundo: Carga de datos
         */
        Caja_de_vehiculos.cargar_vehiculos();
        Cabina cabina1 = new Cabina(3, reloj, "este");
        Cabina cabina2 = new Cabina(5, reloj, "oeste");
        Cabina cabina3 = new Cabina(6, reloj, "este");
        Cabina cabina4 = new Cabina(7, reloj, "oeste");
        Peaje.listaCabinas.add(cabina1);
        Peaje.listaCabinas.add(cabina2);
        Peaje.listaCabinas.add(cabina3);
        Peaje.listaCabinas.add(cabina4);
        Peaje.listaTelepeaje.add(telepeajeEste);
        Peaje.listaTelepeaje.add(telepeajeEste2);
        Peaje.listaTelepeaje.add(telepeajeOeste);
        Peaje.listaTelepeaje.add(telepeajeOeste2);
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

        cabina1.start();
        cabina2.start();
        cabina3.start();
        cabina4.start();

        habilitadorDeCabinas.start();

        /**
         * Cuarto: Guardado de datos Al completarse el pasaje de todos los
         * vehiculos, el logger guarda la salida y los logs en un archivo cada
         * uno
         */
    }
}