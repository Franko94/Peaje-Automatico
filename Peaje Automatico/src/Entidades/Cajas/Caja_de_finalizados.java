/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades.Cajas;

import Entidades.Vehiculo;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import javax.swing.JOptionPane;

/**
 *
 * @author Agustín Picos
 */
public class Caja_de_finalizados {

    public static Queue<Vehiculo> cola = new LinkedList<>();
    
    public static synchronized void addVehiculo(Vehiculo v){
        cola.add(v);
    }

    public Caja_de_finalizados() {
    }
    /**
     * el metodo toma los autos de la cola y los pasa a string para guardarlos
     * para variar los campos, se debe modificar el metodo pasar a string de la 
     * clase vehiculo
     */
    public void guardarAutosEnArchivo() {
        FileWriter fw;
        try {
            fw = new FileWriter("src\\Escenarios\\archivo_salida_vehiculos.txt", true);
            BufferedWriter bw = new BufferedWriter(fw);
            while (!cola.isEmpty()) {
                String lineaActual = cola.poll().pasar_a_String();
                bw.write(lineaActual);
                bw.newLine();
            }
            bw.close();
            fw.close();
        } catch (IOException e) {
            System.out.println("Error al escribir el archivo "
                    + "src\\Escenarios\\archivo_salida_vehiculos.txt");
            e.printStackTrace();
        }
    }
}
