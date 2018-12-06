/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca;
import java.util.ArrayList;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
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
        
        c1.Afisare();
        
        ArrayList<Carte> List = new ArrayList();
        
        for(int i=0; i<3 ; i++){
            Carte cc = new Carte();
            cc.setTitlu("Vraciul" + i);
            cc.setAutor("Dumbledor");
            List.add(cc);
        }
        
        for(int i=0; i<List.size() ; i++){
            Carte c2;
            c2=List.get(i);
            c2.Afisare();
            
        }
        
//        String fileName = "carti.bin";
//        
//         try {
//            ObjectOutputStream os =  new ObjectOutputStream(new FileOutputStream(fileName));
//            os.writeObject(c1);
//            os.close();
//        } catch (IOException ex) {
//            Logger.getLogger(Biblioteca.class.getName()).log(Level.SEVERE, null, ex);
//        }
        
    }
    
}
