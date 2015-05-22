/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sdsimpl;

import it.unisa.dia.gas.jpbc.Element;
import it.unisa.dia.gas.jpbc.Pairing;
import it.unisa.dia.gas.plaf.jpbc.pairing.PairingFactory;
import java.util.ArrayList;

/**
 *
 * @author ymez76
 */
public class FirstLevelEncDec {
    
    private ProxyParams params;
    private Keys keys;
    private Pairing pairing; 
    private Element message;
    
    
    public void initialize(Keys keys,ProxyParams params ) {
   
       this.params = params;
       this.pairing = PairingFactory.getPairing(this.params.getcurveParams());
       this.keys = keys;
       
   }
    
    public Element GenMessage() {
     
          message = this.params.getGT().newRandomElement();
          System.out.println("Message :" + message);
        return this.message;
      }
    
    
    public ArrayList<Element> encryption(Element plaintxt, Keys keys){

       Element k,gk,zk,zak,c2;
      ArrayList<Element> ciphertxt = new ArrayList<Element>();

       k = this.params.getZr().newRandomElement().getImmutable();
       gk = this.params.getgpre().powZn(k).getImmutable();
       zk = this.pairing.pairing(this.params.getg(),gk).getImmutable();
       zak = this.pairing.pairing(this.keys.getPk().getImmutable(),gk);  // z_a_k = c1
       
       c2 = zk.mul(plaintxt);
       ciphertxt.add(zak);
       ciphertxt.add(c2);
       
       System.out.println("k   : " + k);
       System.out.println("z^k : " + zk);
       System.out.println("z^ak: " + zak);
       System.out.println("plaintxt    : " + plaintxt);
       
       
       System.out.println("ciphertxt 0 :" + ciphertxt.get(0));
       System.out.println("ciphertxt 1 :" + ciphertxt.get(1));
       
       return ciphertxt;
      
      }
    
    public Element decryption(ArrayList<Element> ciphertxt, Keys keys){

        Element i_alpha, dec_message;
        i_alpha = ciphertxt.get(0).powZn(this.keys.getIsk());
        dec_message = ciphertxt.get(1).div(i_alpha);
       return dec_message;
      }
 
    
}
