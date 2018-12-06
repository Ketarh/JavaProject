/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca;

/**
 *
 * @author avent
 */
public class Carte {
    private String titlu;
    private String autor;
    public int pret;
    public int an;
    
    Carte(){
        
    }
    
    Carte(String t, String a, int p, int an){
        titlu = t;
        autor = a;
        pret = p;
        this.an = an;              
    }
    
    public void setTitlu(String t){
        titlu = t;
    }
    public void setAutor(String a){
        autor = a;
    }
    public void setPret(int p){
        pret = p;
    }
    public void setAn(int an){
        this.an = an;
    }
    
    public String getTitlu(){
        return titlu;
    }
    public String getAutor(){
        return autor;
    } 
    public int getPret(){
        return pret;
    } 
    public int getAn(){
        return an;
    } 
    
    void Afisare(){
        System.out.println(titlu);
        System.out.println(autor);
        System.out.println(an);
        System.out.println(pret);
    }
}
