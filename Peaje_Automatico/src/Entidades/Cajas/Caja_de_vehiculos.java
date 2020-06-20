/**
 * este objeto carga los behiculos desde un txt y los almacena
 * tambien tiene un metodo para liberarlos. almacena en una cola
 */
package Entidades.Cajas;

import Entidades.Proyecto_peaje;
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

    private static Queue<Vehiculo> colaEste = new LinkedList<>();
    private static Queue<Vehiculo> colaOeste = new LinkedList<>();

    public static void cargar_vehiculos() {
        File archivo;
        FileReader fr;
        BufferedReader br;
        String linea;

        try {
            archivo = new File("src\\Escenarios\\prueba.csv");
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);
            int i = 0;
            while ((linea = br.readLine()) != null) {
                Vehiculo vehiculo = new Vehiculo(linea); //ACA MODIFICAR LOS DATOS QUE CREAN EL AUTO
                agregarVehiculo(vehiculo);
                Proyecto_peaje.cantidadEntrada++;
            }
            br.close();
            fr.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Hubo un error leyendo el archivo" + e);
        }
    }

    public static void agregarVehiculo(Vehiculo v) {
        if (v.getDireccion().equalsIgnoreCase("este")) {
            colaEste.add(v);
        } else {
            colaOeste.add(v);
        }
    }
    public static Vehiculo getVehiculo(String sentido) {
        if (sentido.equalsIgnoreCase("este")) {
            return colaEste.poll();
        } else {
            return colaOeste.poll();
        }
    }
    public static boolean estaVacia(String sentido){
        if (sentido.equalsIgnoreCase("este")) {
            return colaEste.isEmpty();
        } else {
            return colaOeste.isEmpty();
        }
    }
}
