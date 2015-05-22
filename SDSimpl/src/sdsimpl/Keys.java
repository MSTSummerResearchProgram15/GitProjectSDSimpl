/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sdsimpl;

import it.unisa.dia.gas.jpbc.Element;

/**
 *
 * @author ymez76
 */
public class Keys {
    
    private Element pk;
    private Element sk;
    private Element rkA_B;
    private Element Isk;
    
    public Keys(Element pk, Element sk, Element rkA_B, Element Isk){
        this.pk = pk;
        this.sk = sk;
        this.rkA_B = rkA_B;
        this.Isk = Isk;
    
    }
    
    
    
     public Keys(Element pk, Element sk, Element Isk){
        this.pk = pk;
        this.sk = sk;
        this.Isk = Isk;
    
    }
    
    public Element getPk(){
        return pk;
    }
    
    public Element getSk(){
        return sk;
    }
    
    public Element getrkA_B(){
        return rkA_B;
    }
    
    public Element getIsk(){
        return Isk;
    }
    
    void setrkA_B(Element rkA_B){
        this.rkA_B = rkA_B;
    }
    
     public void Info(){
        
        System.out.println("Keys ------------");
        System.out.println("pk   :" + this.pk);
        System.out.println("sk   :" + this.sk);
        System.out.println("Isk  :" + this.Isk);
        System.out.println("rkA_B:" + this.rkA_B);
        
        System.out.println("-----------------");
  
                  
    }
        
}
