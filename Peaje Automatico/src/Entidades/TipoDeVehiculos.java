/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.util.HashMap;
import java.util.Map;
import javax.xml.catalog.Catalog;

/**
 *
 * @author Teo
 */
public enum TipoDeVehiculos {
    
    auto (20),
    moto (10),
    camion (30),
    omnibus (25);
        
    public final int label;
        
    private TipoDeVehiculos(int label){
        this.label = label;
    }
    
}
