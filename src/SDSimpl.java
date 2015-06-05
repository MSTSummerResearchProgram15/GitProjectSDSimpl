/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import it.unisa.dia.gas.jpbc.Element;
import java.util.ArrayList;

/**
 * @author ymez76
 **/

public class SDSimpl {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        
        // generate the general parameters for all users
        
        ProxyParamsGen param = new ProxyParamsGen();
        ProxyParams params;
        param.init();
        params= param.genParams();
        
        
        // generate keys for all (data owner and users)
        
        KeyGen keygen = new KeyGen();
        Keys keys, keys1,keys2;
                
    
        keygen.init(params);
        
        // generate keys for the data owner
        keys = keygen.keyGen(params);
        System.out.println("Data Owner Keys");
        keys.Info();
        
        // generate keys for the user 1
        keys1 = keygen.keyGen(params);
        System.out.println("User 1 Keys");
        keys1.Info();
       
        // generate keys for the user 2
        keys2 = keygen.keyGen(params);
        System.out.println("User 2 Keys");
        keys2.Info();
       
        
   // Re encryption key generation
        
             
        ProxyKeyGen proxyparam = new ProxyKeyGen();
        Element rkA_B;
        // Re encryption key generation for user 1
        rkA_B = proxyparam.proxyKeyGen(keys, keys1, params);
        keys1.setrkA_B(rkA_B);
        System.out.println("User 1 after generated rkA_B ");
        keys1.Info();
        // Re encryption key generation for user 2
        rkA_B = proxyparam.proxyKeyGen(keys, keys2, params);
        keys2.setrkA_B(rkA_B);
        System.out.println("User 2 after generated rkA_B ");
        keys2.Info();
        
   // re-encryption
   // 1- create msg and encrypt it to 2nd level using owner keys
        // the encrypted mes will be outsourced to the cloud
       SecondLevelEncDec second = new SecondLevelEncDec();
       Element plaintxt;
       ArrayList<Element> ciphertxt = new ArrayList<Element>();
       ArrayList<Element> ciphertxt1 = new ArrayList<Element>(); 
       ArrayList<Element> ciphertxt2 = new ArrayList<Element>();
      
       second.initialize(keys, params);
       plaintxt = second.GenMessage();  // generate one message
       
       ciphertxt =second.encryption(plaintxt, keys);
       
       System.out.println("plaintxt     : " + plaintxt);
       System.out.println("in 2nd Level ");
       System.out.println("ciphertxt 0  : " + ciphertxt.get(0));
       System.out.println("ciphertxt 1  : " + ciphertxt.get(1));
       
               
   // 2- convert from 2nd to 1st level to each user by using ReEncryption key
       // to user 1
       
       ReEncryption reencryption1 = new ReEncryption();
      // reencryption.init(keys1, params);
       ciphertxt1 = reencryption1.reencryption(keys1, params, ciphertxt);
       System.out.println("User 1 ");
       System.out.println("convert to 1st Level ");
       System.out.println("ciphertxt 0 " + ciphertxt1.get(0));
       System.out.println("ciphertxt 1 " + ciphertxt1.get(1));
     
 /*            
       // to user 2
   //   reencryption.init(keys2, params);
       ReEncryption reencryption2 = new ReEncryption();
       ciphertxt2 = reencryption2.reencryption(keys2, params, ciphertxt);
       System.out.println("User 2 ");
       System.out.println("convert to 1st Level ");
       System.out.println("ciphertxt 0 " + ciphertxt2.get(0));
       System.out.println("ciphertxt 1 " + ciphertxt2.get(1));
   */
       
   // 3- decrypt it using 1st level decryption
       // to user 1
       FirstLevelEncDec first= new FirstLevelEncDec();
       first.initialize(keys1, params);
       Element msg;
       msg = first.decryption(ciphertxt, keys1);
       System.out.println("User 1 ");
       System.out.println("decrypt from 1st Level ");
       System.out.println("ciphertxt 0 " + ciphertxt.get(0));
       System.out.println("ciphertxt 1 " + ciphertxt.get(1));
    // 4- compare original msg with decrypted 1st level msg
       System.out.println("Decrypt : " + msg);
       System.out.println("Original: " + plaintxt);

    }
}