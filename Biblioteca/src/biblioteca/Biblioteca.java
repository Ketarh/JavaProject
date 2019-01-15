/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *
 * @author avent
 */
public class Biblioteca {
    
    
    
    

    /**
     * @param args
     * @throws java.io.FileNotFoundException
     * @throws java.lang.ClassNotFoundException
     */
    
   
    
    public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
        // TODO code application logic here
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginMenu().setVisible(true);
            }
        });
        
        

    }

}