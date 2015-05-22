/**
 *
 * @author Yousef Elmehdwi, ymez76@mst.edu, yelmehdwi@yahoo.com
 */
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sdsimpl;

import it.unisa.dia.gas.jpbc.Element;
import it.unisa.dia.gas.jpbc.Pairing;
import it.unisa.dia.gas.plaf.jpbc.pairing.PairingFactory;


public class ProxyKeyGen {
  
    private ProxyParams Owner_params;
    private Keys okeys,ukeys;
    private Pairing pairing;
    
    
   public Element proxyKeyGen(Keys Owner_keys, Keys User_keys , ProxyParams Owner_params ) {
   
       this.Owner_params = Owner_params;
       this.pairing = PairingFactory.getPairing(this.Owner_params.getcurveParams());
       this.okeys = Owner_keys;
       this.ukeys = User_keys;
       Element rkA_B = this.ukeys.getPk().powZn(this.okeys.getIsk()).getImmutable();
     return rkA_B; //new Keys(this.ukeys.getPk(),this.ukeys.getSk(),this.ukeys.getIsk(),rkA_B);
   }
   
   
}
