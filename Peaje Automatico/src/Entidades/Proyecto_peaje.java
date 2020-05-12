/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

/**
 *
 * @author Agustín Picos
 */
public class Proyecto_peaje {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Vehiculo v = new Vehiculo("matricula,tipo,true,false");
        Caja_de_vehiculos cajav = new Caja_de_vehiculos();
        cajav.cargar_vehiculos();
        Caja_de_finalizados cajaf = new Caja_de_finalizados();
        cajaf.add_Vehiculo(v);
        cajaf.escribirArchivo();
        System.out.println("");
    }

}
