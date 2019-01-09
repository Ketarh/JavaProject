/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca;
//import java.io.Serializable;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
/**
 *
 * @author avent
 */
public class Carte implements Serializable {
//    private static final long serialVersionUID = 1L;
    public String titlu;
    public String autor;
    public String editura;
    public String rezumat;
    public int pret;
    public int an;
    public int cantitate;
//    public LocalDateTime dayOfBorrow;
//    public LocalDateTime dayOfReturn;
    //String timeStamp = new SimpleDateFormat("yyyyMMdd").format(Calendar.getInstance().getTime());
    //Scanner scan =new Scanner(System.in);
    
    
    
    public Carte(){
        
    }
    
    public Carte(String t, String rez, int a){
        titlu = t;
        rezumat = rez;
        an = a;
    }
    
    public Carte(String t, String a, String e, String rez, int p, int cant, int an){
        titlu = t;
        autor = a;
        editura = e;
        rezumat = rez;
        pret = p;
        cantitate = cant;
        this.an = an;              
    }
    public void setTitlu(String n){
        this.titlu = n;
    }
    public void setAutor(String a){
        this.autor = a;
    }
    public void setEditura(String e){
        this.editura = e;
    }
    public void setRezumat(String r){
        this.rezumat = r;
    }
    public void setPret(int p){
        pret = p;
    }
    public void setAn(int an){
        this.an = an;
    }
    public void setCant(int c){
        cantitate = c;
    }
//    public void setDOB(){
//        dayOfBorrow = LocalDateTime.now();
//    }
//    public void setDOR(int i){
//        dayOfReturn = dayOfBorrow.plusDays(i);
//    }
    
    public String getTitlu(){
        return titlu;
    }
    public String getAutor(){
        return autor;
    } 
    public String getEditura(){
        return editura;
    }
    public String getRezumat(){
        return rezumat;
    }
    public int getPret(){
        return pret;
    } 
    public int getAn(){
        return an;
    } 
    public int getCantitate(){
        return cantitate;
    }
//    public LocalDateTime getDOB(){
//        return dayOfBorrow;
//    }
//    public LocalDateTime getDOR(){
//        return dayOfReturn;
//    }
    
    
    void Afisare(){
        System.out.println(titlu);
        System.out.println(autor);
        System.out.println(editura);
        System.out.println(rezumat);
        System.out.println(an);
        System.out.println(pret);
        System.out.println(cantitate);
    }
}
