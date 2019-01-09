/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

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
        //Time time = new Time();
        ArrayList<Time> dates = new ArrayList<>();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	LocalDateTime now = LocalDateTime.now();
        LocalDateTime day1;
        //LocalDateTime day2;
	//System.out.println(dtf.format(now)); //09/01/2019
        
        
        day1 = now.plusDays(70); 
        //day2 = day1.plusYears(2); 
       // System.out.println(dtf.format(day1)); //20/03/2019
        //System.out.println(dtf.format(day2)); //20/03/2021
        
//        time.setFirstDay(now); //09/01/2019
//        time.setReturnDay(70); //20/03/2019
        

        dates.add(new Time(now, 70));
        
//        time.setFirstDay(day2); 
//        time.setReturnDay(2);
        
        dates.add(new Time(day1, 1));
        
       // System.out.println(dtf.format(day1)); //20/03/2019
        //day2 = day1.plusYears(1); 
        //System.out.println(dtf.format(day2)); //20/03/2020
        System.out.println(dtf.format(dates.get(0).getFirstDay())); //09/01/2019
        System.out.println(dtf.format(dates.get(0).getReturnDay())); //20/03/2019
        System.out.println(dtf.format(dates.get(1).getFirstDay())); //20/03/2019
        System.out.println(dtf.format(dates.get(1).getReturnDay())); //21/03/2019
        

    }

}