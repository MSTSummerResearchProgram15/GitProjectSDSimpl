/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sdsimpl;

import it.unisa.dia.gas.jpbc.Pairing;
import it.unisa.dia.gas.jpbc.Element;
import it.unisa.dia.gas.plaf.jpbc.pairing.PairingFactory;

/**
 *
 * @author ymez76
 */
public class KeyGen {
    private ProxyParams params;
    private Keys keys;
    private Pairing pairing;
 
    
      
   public void initialize(Keys keys,ProxyParams params ) {
   
       this.params = params;
       this.pairing = PairingFactory.getPairing(this.params.getcurveParams());
       this.keys = keys;
       
   }
   
    public void init(ProxyParams params ) {
   
       this.params = params;
       this.pairing = PairingFactory.getPairing(this.params.getcurveParams());
       
   }
    
   
    public Keys keyGen(ProxyParams params) {
        
        Element sk  = this.params.getZr().newRandomElement().getImmutable();
        Element pk  = this.params.getgpre().powZn(sk).getImmutable();
        Element Isk = sk.invert().getImmutable();
       
        return new Keys(pk,sk,Isk);
        
       }
    
    
       
   
}
