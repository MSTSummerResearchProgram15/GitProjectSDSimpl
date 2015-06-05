/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


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
    	Element sk = null;
    	Element pk = null;
    	Element Isk = null;
        try{
        sk  = this.params.getZr().newRandomElement().getImmutable();
        pk  = this.params.getgpre().powZn(sk).getImmutable();
        Isk = sk.invert().getImmutable();
        }
        catch(Exception ex){
        	ex.printStackTrace();
        }
       
        return new Keys(pk,sk,Isk);
        
       }
    
    
       
   
}
