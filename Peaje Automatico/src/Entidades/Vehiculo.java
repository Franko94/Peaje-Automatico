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
public class Vehiculo {
    private boolean unidad_especial;
    private String tipo;
    private boolean genera_accidente;
    private long hora_comienzo;
    private long hora_fin;
    private String matricula;
    
    public Vehiculo(String linea){
        String[]datos = linea.split(",");
        this.matricula = datos[0];
        this.tipo = datos[1];
        this.unidad_especial = Boolean.valueOf(datos[2]);
        this.genera_accidente = Boolean.valueOf(datos[3]);
    }
    public String pasar_a_String(){
        String dato="";
        dato+=String.valueOf(matricula)+",";
        dato+=tipo+",";
        dato+=String.valueOf(unidad_especial)+",";
        dato+=String.valueOf(genera_accidente)+",";
        dato+=String.valueOf(hora_fin-hora_comienzo);
        return dato;
    }

    public boolean isUnidad_especial() {
        return unidad_especial;
    }
    
    public void setHoraFin(long numero){
        hora_fin = numero;
    }
    
    public String getTipo(){
        return tipo;
    }
    
    
    
    
}
