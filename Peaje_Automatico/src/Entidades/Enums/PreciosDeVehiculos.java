/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades.Enums;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Teo
 */
public enum PreciosDeVehiculos {
    
    auto (20),
    moto (10),
    camion (30),
    omnibus (25);
        
    public final int label;
        
    private PreciosDeVehiculos(int label){
        this.label = label;
    }
    
}