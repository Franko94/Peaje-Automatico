/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logger;


import Entidades.Peaje;
import Entidades.Proyecto_peaje;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author fran_
 */
public class Logger {

    private static final String RUTALOG = "src\\Escenarios\\log.csv";
    private static final String RUTAVEHICULO = "src\\Escenarios\\archivo_salida_vehiculos.txt";
    private static final Queue<String> COLALOG = new LinkedList<>();
    private static final Queue<String> COLAVEHICULOS = new LinkedList<>();

    public synchronized static void agregarLog(String linea) {
        COLALOG.add(linea);
    }

    public synchronized static String getLog() {
        return COLALOG.poll();
    }

    public synchronized static boolean isVaciaLog() {
        return COLALOG.isEmpty();
    }
    public synchronized static void agregarVehiculo(String linea) {
        COLAVEHICULOS.add(linea);
        Proyecto_peaje.cantidadSalida ++;
        if(Proyecto_peaje.cantidadSalida == Proyecto_peaje.cantidadEntrada){
            //guardarVehiculos();
            //log();
        }
    }

    public synchronized static String getVehiculo() {
        return COLAVEHICULOS.poll();
    }

    public synchronized static boolean isVaciaVehiculos() {
        return COLAVEHICULOS.isEmpty();
    }

    public static void log() {
        FileWriter fw;
        try {
            fw = new FileWriter(RUTALOG, true);
            try (BufferedWriter bw = new BufferedWriter(fw)) {
                while (!isVaciaLog()) {
                    String lineaActual = getLog();
                    bw.append(lineaActual);
                    bw.newLine();
                }
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Error al escribir el archivo "
                    + RUTALOG);
        }
    }
    public static void guardarVehiculos() {
        FileWriter fw;
        try {
            fw = new FileWriter(RUTAVEHICULO, true);
            try (BufferedWriter bw = new BufferedWriter(fw)) {
                while (!isVaciaVehiculos()) {
                    String lineaActual = getVehiculo();
                    bw.append(lineaActual);
                    bw.newLine();
                }
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Error al escribir el archivo "
                    + RUTAVEHICULO);
        }
    }
}
