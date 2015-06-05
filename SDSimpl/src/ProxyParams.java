/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import it.unisa.dia.gas.jpbc.Element;
import it.unisa.dia.gas.jpbc.ElementPowPreProcessing;
import it.unisa.dia.gas.jpbc.Field;
import it.unisa.dia.gas.jpbc.Pairing;
import it.unisa.dia.gas.jpbc.PairingParameters;
import it.unisa.dia.gas.jpbc.PairingParametersGenerator;
import it.unisa.dia.gas.plaf.jpbc.pairing.PairingFactory;
//import it.unisa.dia.gas.plaf.jpbc.pairing.CurveParams;


/**
 *
 * @author ymez76
 */
public class ProxyParams {

    
    private PairingParameters params;
    private Field g1;
    private Field gt;
    private Field zr;
   // private Element k;
    private Element g;
    private ElementPowPreProcessing gpre;
   // private Element g_k;
   // private Element z_k;
    private Pairing pairing;
 
    
    public ProxyParams(PairingParameters params){
           this.params = params;
           
    }
    
    public PairingParameters getcurveParams(){
           return params;
    }
    
    
    public Field getG1() {
           return g1;
    }
    public Field getGT() {
           return gt;
    }
    public Field getZr() {
           return zr;
    }
    
    void setG1(Field g1) {
           this.g1 = g1;
    }
    
    void setGT(Field gt) {
           this.gt = gt;
    } 
    
    void setZr(Field zr) {
           this.zr = zr;
    }
   
     void setPairing(Pairing pairing) {   // Do I really need this, I think, to generate PK, 
           this.pairing = pairing;
    }
   
     
   void setg(Element g) {
           this.g = g;
    }
   
   public Element getg() {
           return this.g;
    }
   
      
    void setgpre(ElementPowPreProcessing gpre) {
           this.gpre = gpre;
    }    
    
    public ElementPowPreProcessing getgpre() {
           return gpre;
    } 
    
       
        
    // Gerry idea to print out 
  /*   public void Info(){
        
        System.out.println("ProxyParameters");
        System.out.println("k   :" + this.k);
        System.out.println("g^k :" + this.g_k);
        System.out.println("z^k :" + this.z_k);
        System.out.println("-----------------");
  
                  
    }*/
    
}
