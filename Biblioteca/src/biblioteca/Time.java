/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 *
 * @author avent
 */
public class Time implements Serializable{
        	
        LocalDateTime firstDay;
        LocalDateTime returnDay;
        String totalPrice;
        
        public LocalDateTime getFirstDay(){
            return firstDay;
        }
        
        public LocalDateTime getReturnDay(){
            return returnDay;
        }
        
        public String getTotalPrice(){
            return totalPrice;
        }
        
        public void setReturnDay(int k){
            returnDay = firstDay.plusDays(k);
        }
        
        public void setFirstDay(LocalDateTime T){
            firstDay = T;
        }
        
        public Time(){
            
        }
        
        public Time(LocalDateTime T1, int p ,int x){
            int total;
            
            firstDay = T1;           
            returnDay = firstDay.plusDays(p);
            
            if(p > 14){
            
            total = (p - 14) * x;
            totalPrice = Integer.toString(total) + " lei";
            
        }else totalPrice = "Gratuit";
        }
        
}
