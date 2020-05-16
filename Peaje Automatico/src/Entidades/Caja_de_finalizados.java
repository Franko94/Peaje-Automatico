/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

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

    Queue<Vehiculo> cola = new LinkedList<>();

    public Caja_de_finalizados() {
    }
    public void add_Vehiculo(Vehiculo v) {
        cola.add(v);
    }

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
