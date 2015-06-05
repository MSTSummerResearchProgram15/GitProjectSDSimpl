/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import it.unisa.dia.gas.jpbc.Element;
import it.unisa.dia.gas.jpbc.ElementPowPreProcessing;
import it.unisa.dia.gas.jpbc.Field;
import it.unisa.dia.gas.jpbc.Pairing;
import it.unisa.dia.gas.jpbc.PairingPreProcessing;

/**
 *
 * @author ymez76
 */
public class KeyGeneration { 
/*  
 *
 * @author ymez76
 */

    
public void KeyGeneration(Field G1, Field GT , Field Zr,Pairing pairing,Element g, ElementPowPreProcessing gPre){
         
  // do stuff here
 
//******************************
//    Key Generation Phase
//******************************
                // 1- Choose random secret key 
                // Choose random value in Zr
                // a is secret key for Alice, Database owner
                Element a = Zr.newRandomElement();   // Yousef make sure which better: Zr.newElement().setToRandom();
                // b is secret key for Bob, Database consumer/user
                Element b = Zr.newRandomElement();   // Yousef make sure which better: Zr.newElement().setToRandom();
                
                // 2- Compute the corresponding public key
                // Computes the public key g^x
                // Note that here use gPre to take advantage of the pre computation.
                // Use the Preprocessing to speed up the computation
                
                // pk_a is public key for Alice, Database owner
                Element pk_a = gPre.powZn(a);
                //************PairingPreProcessing pkPairing_a = pairing.pairing(pk_a);
                
                // pk_b is public key for Bob, Database consumer/user
                Element pk_b = gPre.powZn(b);
                //*************PairingPreProcessing pkPairing_b = pairing.pairing(pk_b);
                
          /*  
           Disply the keys to make sure that every thing is ok
                     
           */
                System.out.println("sk_a"+a.toString());
                System.out.println("length sk_a"+a.toString().length());
                System.out.println("pk_a"+pk_a.toString());
                System.out.println("length pk_a"+pk_a.toString().length());
                System.out.println("sk_b"+b.toString());
                System.out.println("length sk_b"+b.toString().length());
                System.out.println("pk_b"+pk_b.toString());
                System.out.println("length pk_b"+pk_b.toString().length());
                
               // ArrayList<Element> key = new ArrayList<Element>();
	       // key.add(g);
}
               
}
