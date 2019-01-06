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
        Carte c1, c2;
        c1 = new Carte();
        c2 = new Carte();
        c1.setAutor("Mike");
        c1.setEditura("Corint");
        c1.setTitlu("Poveste");
        c1.setRezumat("o poveste cu Mike");
        c1.setAn(123);
        c1.setCant(21);
        c1.setPret(2);
        c1.Afisare();
        c2.Afisare();
        c2 = c1;
        c1.Afisare();
        c2.Afisare();
        
        
    }

}