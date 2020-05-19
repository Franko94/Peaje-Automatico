/**
 * este objeto carga los behiculos desde un txt y los almacena
 * tambien tiene un metodo para liberarlos. almacena en una cola
 */
package Entidades.Cajas;

import Entidades.Vehiculo;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import javax.swing.JOptionPane;

/**
 *
 * @author Agustín Picos
 */
public class Caja_de_vehiculos {
    
    public static Queue<Vehiculo> cola = new LinkedList<>();

    public static void cargar_vehiculos() {
        File archivo;
        FileReader fr;
        BufferedReader br;
        String linea;

        try {
            archivo = new File("src\\Escenarios\\78000.csv");
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);
            while ((linea = br.readLine()) != null) {
                Vehiculo vehiculo = new Vehiculo(linea); //ACA MODIFICAR LOS DATOS QUE CREAN EL AUTO
                cola.add(vehiculo);
            }
            br.close();
            fr.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Hubo un error leyendo el archivo" + e);
        }
    }
}
