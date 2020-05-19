/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logger;

import Entidades.Vehiculo;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import static java.lang.Thread.sleep;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.logging.Level;

/**
 *
 * @author fran_
 */
public class Logger {

    private static final String RUTA = "src\\Escenarios\\log.csv";

    public static void log(String linea) {
        FileWriter fw;
        try {
            sleep(3);
        } catch (InterruptedException ex) {
            java.util.logging.Logger.getLogger(Logger.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            fw = new FileWriter(RUTA, true);
            BufferedWriter bw = new BufferedWriter(fw);
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            
            
           String lineaActual = linea + "," + now;
            bw.write(lineaActual);
            bw.newLine();
            bw.close();
            fw.close();
        } catch (IOException e) {
            System.out.println("Error al escribir el archivo "
                    + RUTA);
            e.printStackTrace();
        }
    }

}
