/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades.Cajas;

import Entidades.Colas.Cola_Comun_Ruta;
import Entidades.Peaje;
import Entidades.Vehiculo;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Agustín Picos
 */
public class Caja_de_finalizados extends Thread{

    public static Queue<Vehiculo> cola = new LinkedList<>();
    public static int i = 0;
    
    synchronized public static void addVehiculo(Vehiculo v){
        Caja_de_finalizados.cola.add(v);
    }

    
    @Override
    public void run(){
        while(true){
            if(!cola.isEmpty()){
                guardarAutosEnArchivo();
            }
            else{
                try {
                    sleep(100);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Caja_de_finalizados.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            System.out.println(Cola_Comun_Ruta.cola.size());
        }
    }
    
    /**
     * el metodo toma los autos de la cola y los pasa a string para guardarlos
     * para variar los campos, se debe modificar el metodo pasar a string de la 
     * clase vehiculo
     */
    synchronized public static void guardarAutosEnArchivo() {
        FileWriter fw;
        try {
            fw = new FileWriter("src\\Escenarios\\archivo_salida_vehiculos.txt", true);
            BufferedWriter bw = new BufferedWriter(fw);
            while (cola.size() > 0) {
                String lineaActual = cola.poll().pasar_a_String();
                bw.write(lineaActual);
                bw.newLine();
                System.out.println("linea " +i);
                i++;
            }
            bw.close();
            fw.close();
        } catch (IOException e) {
            System.out.println("Error al escribir el archivo "
                    + "src\\Escenarios\\archivo_salida_vehiculos.txt");
            e.printStackTrace();
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
}
