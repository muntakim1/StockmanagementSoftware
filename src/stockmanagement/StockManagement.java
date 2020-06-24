/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stockmanagement;

import java.sql.Connection;

/**
 *
 * @author munta
 */
public class StockManagement {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       java.awt.EventQueue.invokeLater(() -> {
          
           new Ui().setVisible(true);
        
       });
    }
    
}
