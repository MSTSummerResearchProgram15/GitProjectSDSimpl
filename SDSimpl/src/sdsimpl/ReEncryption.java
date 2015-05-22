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
public class ReEncryption {
    
    private ProxyParams params;
    private Keys keys;
    private Pairing pairing; 
  //  private Element[] message;
    
    
    public ArrayList<Element> reencryption(Keys keys,ProxyParams params,ArrayList<Element> ciphertxt  ) {
   
       Element c1;
       this.params = params;
       this.pairing = PairingFactory.getPairing(this.params.getcurveParams());
       this.keys = keys;
      
       c1 = this.pairing.pairing(ciphertxt.get(0),this.keys.getrkA_B());
       ciphertxt.remove(0);
       ciphertxt.add(0, c1);
       return ciphertxt;
  
   }
    
    public void init(Keys keys,ProxyParams params ) {
   
       this.params = params;
       this.pairing = PairingFactory.getPairing(this.params.getcurveParams());
       this.keys = keys;
       
   }
    
   
    
    
 
}
