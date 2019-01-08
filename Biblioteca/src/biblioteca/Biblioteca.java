/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 *
 * @author avent
 */
public class Biblioteca {
    public static ArrayList<Carte> carte = new ArrayList<>();
    public static ArrayList<Carte> carte2 = new ArrayList<>();
    public static File write = new File("wCarti.txt");
   // public static Carte c;
    /**
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
     * @throws java.lang.ClassNotFoundException
     */
    
    static void writeFile() throws FileNotFoundException, IOException{
        
        FileOutputStream fileOutput = new FileOutputStream(write);
        ObjectOutputStream output = new ObjectOutputStream(fileOutput);
        
        output.reset();
        
        for(Carte ccc: carte){
            output.writeObject(ccc);
        }
        fileOutput.close();
        output.close();
        
        
    }
    
    public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
        // TODO code application logic here
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginMenu().setVisible(true);
            }
        });
//        Carte c1, c2;
//        c1 = new Carte();
//        c2 = new Carte();
//        c1.setAutor("Mike");
//        c1.setEditura("Corint");
//        c1.setTitlu("Poveste");
//        c1.setRezumat("o poveste cu Mike");
//        c1.setAn(123);
//        c1.setCant(21);
//        c1.setPret(2);
//        c1.Afisare();
//        c2.Afisare();
//        c2 = c1;
//        c1.Afisare();
//        c2.Afisare();
        
        carte.add(new Carte("Colt Alb", "Un lup singuratic \nCare supravietuieste in padure", 1990));
        carte.add(new Carte("Marea Moarta", "Scurt documentar despre \nMarea\nMoarta", 2001));
        carte.add(new Carte("Angelo si prietenii", "Testam carti", 9999));
        carte.add(new Carte("Test Final", "sa vedem acum", 1234));
        
        carte.get(0).Afisare();
        carte.get(1).Afisare();
        carte.get(2).Afisare();
        carte.get(3).Afisare();
        
        
//        FileOutputStream fileOutput = new FileOutputStream(write);
//        ObjectOutputStream output = new ObjectOutputStream(fileOutput);
//        
//        
//        for(Carte ccc: carte){
//            output.writeObject(ccc);
//        }
//        fileOutput.close();
//        output.close();
        
        writeFile();

        FileInputStream fileIn = new FileInputStream(write);
        ObjectInputStream input = new ObjectInputStream(fileIn);
        try {
            while(true){
                Carte c = (Carte)input.readObject();
                carte2.add(c);
            }
        }catch (EOFException ex){
        }
        
        //carte2.remove(2);
        
        carte2.get(0).Afisare();
        carte2.get(1).Afisare();
        carte2.get(2).Afisare();
        carte2.get(3).Afisare();
//        for(Carte c: carte2){
//            c.Afisare();
//        }
    }

}