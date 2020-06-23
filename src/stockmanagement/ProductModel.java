/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stockmanagement;

/**
 *
 * @author munta
 */
public class ProductModel {
      
        public String Product_ID;
        public int Quantity;
       
        
        public ProductModel( String Product_ID,  int Quantity)
        {
            this.Product_ID = Product_ID;
            this.Quantity = Quantity;
            
        }
}
