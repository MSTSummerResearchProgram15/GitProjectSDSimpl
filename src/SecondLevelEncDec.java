/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import it.unisa.dia.gas.jpbc.Element;
import it.unisa.dia.gas.jpbc.Pairing;
import it.unisa.dia.gas.plaf.jpbc.pairing.PairingFactory;
import java.util.ArrayList;

/**
 *
 * @author ymez76
 */
public class SecondLevelEncDec {
    
    private ProxyParams params;
    private Keys keys;
    private Pairing pairing; 
    private Element message;
    
    public void initialize(Keys keys,ProxyParams params ) {
   
       this.params = params;
       this.pairing = PairingFactory.getPairing(this.params.getcurveParams());
       this.keys = keys;
       
   }
      
   /*  public Element[] GenMessage(int msgNumber) {
     
        int i;
        for (i=0;i<=msgNumber;i++){
             this.message[i] = this.params.getGT().newRandomElement();
             System.out.println("Message [" + i + "] :" + message[i]);
           
        }
        return this.message;
      }
     */
      public Element GenMessage() {
     
             this.message = this.params.getGT().newRandomElement();
             System.out.println("Message  :" + this.message);
           
        return this.message;
      }
  
     public ArrayList<Element> encryption(Element plaintxt, Keys keys){

       Element k,gk,zk,zak,c1,c2;
       //Element [] ciphertxt;
       ArrayList<Element> ciphertxt = new ArrayList<Element>();
      
       k = this.params.getZr().newRandomElement().getImmutable();
       gk = this.params.getgpre().powZn(k).getImmutable();
       zk = this.pairing.pairing(this.params.getg(),gk).getImmutable();
       
       c1 = this.keys.getPk().powZn(k);
       c2 = zk.mul(plaintxt);
       ciphertxt.add(c1);
       ciphertxt.add(c2);
       
       System.out.println("k   : " + k);
       System.out.println("z^k : " + zk);
       System.out.println("plaintxt    : " + plaintxt);
       
       System.out.println("ciphertxt 0 " + ciphertxt.get(0));
       System.out.println("ciphertxt 1 " + ciphertxt.get(1));
       
       return (ciphertxt);
       
      }
     
     public Element decryption(ArrayList<Element> ciphertxt, Keys keys){

        Element alpha, i_alpha, dec_message;
        alpha = this.pairing.pairing(ciphertxt.get(0),this.params.getg()).getImmutable();
        i_alpha = alpha.powZn(this.keys.getIsk());
        dec_message = ciphertxt.get(1).div(i_alpha);
       return dec_message;
      }
             
     
}
