/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Usuario
 */
public class HabilitadorDeCabinas extends Thread {
    
    @Override
    public void run() {
        while (true){
            try {
                Thread.currentThread().sleep(1000);
                for(Cabina cabina : Peaje.getListaCabinas() ){
                    if(!cabina.getHabilitada()){
                        cabina.setHabilitada(true);
                    }
                }
                
            } catch (InterruptedException ex) {
                Logger.getLogger(HabilitadorDeCabinas.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }
    
}
