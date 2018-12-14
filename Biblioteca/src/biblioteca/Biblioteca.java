/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 *
 * @author avent
 */
public class Biblioteca {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginMenu().setVisible(true);
            }
        });
        
        Carte c1 = new Carte("Hansel and Grettle", "Frati Grimm", 50, 1987);
        
        //c1.Afisare();
        
        ArrayList<Carte> List = new ArrayList();
        
        for(int i=0; i<2 ; i++){
            Carte cc = new Carte();
            
            List.add(cc);
        }
        
        for(int i=0; i<List.size() ; i++){
            Carte c2;
            c2=List.get(i);
            c2.Afisare();
            
        }
    
    }
}