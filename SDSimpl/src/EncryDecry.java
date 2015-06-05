/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Yousef Elmehdwi, ymez76@mst.edu , yelmehdwi@yahoo.com
 * 
 **/



import it.unisa.dia.gas.jpbc.Element;
import it.unisa.dia.gas.jpbc.ElementPowPreProcessing;
import it.unisa.dia.gas.jpbc.Field;
import it.unisa.dia.gas.jpbc.Pairing;
import it.unisa.dia.gas.jpbc.PairingParameters;
import it.unisa.dia.gas.jpbc.PairingPreProcessing;
//import it.unisa.dia.gas.plaf.jpbc.pairing.CurveParams;
import it.unisa.dia.gas.plaf.jpbc.pairing.PairingFactory;

import java.util.ArrayList;
import java.io.File;
import java.io.FileOutputStream;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.Writer;
import java.io.FileNotFoundException;
import java.io.IOException;



public class EncryDecry {
    
    public Pairing pairing;
    public Field g1;
    public Field gt;
    public Field zr;
    public Element k;
    public Element g;
    public ElementPowPreProcessing gpre;
    public Element g_k;
    public Element z_k;
    public Element z_a_k;
    public Element message;
    public Element pk_a;
    public Element sk_a;
    public Element insk_a;
    public Element rkA_B;
    public ArrayList<Element> keys = new ArrayList<Element>();
    public ArrayList<Element> Pk_keys = new ArrayList<Element>();
    public ArrayList<Element> Sk_keys = new ArrayList<Element>();
    public ArrayList<Element> InSk_keys = new ArrayList<Element>();

    
    //
    //
    //
 public void initilze() {
    
     System.out.println("|||||||||||      Initilization      ||||||||||||||");
     
     PairingParameters curveParams = PairingFactory.getPairingParameters("a_181_603.properties");
      this.pairing = PairingFactory.getPairing(curveParams);
      this.g1 = this.pairing.getG1();
      this.gt = this.pairing.getGT();
      this.zr = this.pairing.getZr();
     // this.k= this.zr.newRandomElement();
      this.g = this.g1.newRandomElement();
      //this.gpre = this.g.pow();
     // this.g_k = this.gpre.powZn(k);
     // this.z_k = this.pairing.pairing(g, g_k);
     // System.out.println("k    :" + this.k);
      System.out.println("g    :" + this.g);
    //  System.out.println("gpre :" + this.gpre);
    //  System.out.println("Z^k  :" + this.z_k);
    //  System.out.println("Z^ak :" + this.z_a_k);
      
            
    }
 
 public Field [] initializatoin() {
    
     System.out.println("|||||||||||      Initilization      ||||||||||||||");
     PairingParameters curveParams = PairingFactory.getPairingParameters("a_181_603.properties");
      this.pairing = PairingFactory.getPairing(curveParams);
      this.g1 = this.pairing.getG1();
      this.gt = this.pairing.getGT();
      this.zr = this.pairing.getZr();
    Field[] initial= null;
       
     
      //  this.k= this.zr.newRandomElement();
     // this.g = this.g1.newRandomElement();
     // this.gpre = this.g.pow();
    //  this.g_k = this.gpre.powZn(k);
     // this.z_k = this.pairing.pairing(g, g_k);
     // System.out.println("k    :" + this.k);
  //    System.out.println("g    :" + this.g);
    //  System.out.println("gpre :" + this.gpre);
    //  System.out.println("Z^k  :" + this.z_k);
    //  System.out.println("Z^ak :" + this.z_a_k);
      initial[0] =  this.pairing.getG1(); //this.g1;
      initial[1] = this.gt;
      initial[2] = this.zr;
      
     return (initial);
    }
    
  public Pairing Pairing() {
    
	     PairingParameters curveParams = PairingFactory.getPairingParameters("a_181_603.properties");
      return(this.pairing = PairingFactory.getPairing(curveParams));
      
    }
  
   public Element [] initElementParam(Field g1, Field gt, Field zr) {
    
      Element [] initial=null;
      this.k= zr.newRandomElement();
      this.g = g1.newRandomElement();
      //this.gpre = this.g.pow();
      //this.g_k = this.gpre.powZn(k);
      //this.z_k = this.pairing.pairing(g, g_k);
      System.out.println("k    :" + this.k);
      System.out.println("g    :" + this.g);
      initial[0] = this.k;
      initial[1] = this.g;
           
     return (initial);
    }
  public Element [] KeyGen(int UserId, Field zr, ElementPowPreProcessing gpre ) {
    
     Element[] initial=null;
      this.sk_a = zr.newRandomElement().getImmutable();
      this.pk_a = this.gpre.powZn(this.sk_a).getImmutable();
      if (UserId==0){
           this.insk_a = this.sk_a.invert().getImmutable();
            }
      initial[0] = this.sk_a;
      initial[1] = this.pk_a;
      initial[2] = this.insk_a;
     System.out.println("sk"+this.sk_a);
     System.out.println("pk"+this.pk_a);
     System.out.println("Ia"+this.insk_a);
     return initial;
  } 
    //
    //
    //
    //public ArrayList<Element> KeyGeneration() {

 
 public void KeyGeneration(int UserId) {
    
     System.out.println("|||||||||||      Key Generation ||||||||||||||");
     sk_a= this.SecKeyGeneration();
     pk_a = this.gpre.powZn(this.sk_a).getImmutable();
    // this.z_a_k = this.pairing.pairing(this.pk_a, this.g_k);
    // store keys in file
      
/*      File file = new File("../keys.txt"); 
      Writer writer = new BufferedWriter(new FileWriter(file));
      String text= UserId+"\t"+sk_a.toString()+"\t"+pk_a.toString();
      writer.write(text);
  */  
    
    //
    
     Pk_keys.add(UserId, this.pk_a);
     Sk_keys.add(UserId, this.sk_a);
     System.out.println("pk_a [" + UserId + "]"+ this.Pk_keys.get(UserId));
     System.out.println("sk_a [" + UserId + "]"+ this.Sk_keys.get(UserId));    
     
     
     
     if (UserId==0){
           insk_a = this.InvDBOwnerSecKey().getImmutable();
           System.out.println("pk_a [0]"+ this.Pk_keys.get(0));
           System.out.println("sk_a [0]"+ this.Sk_keys.get(0));
            }
     if (UserId==1){
           System.out.println("pk_a [0]"+ this.Pk_keys.get(0));
           System.out.println("sk_a [0]"+ this.Sk_keys.get(0));
      
           System.out.println("pk_a [1]"+ this.Pk_keys.get(1));
           System.out.println("sk_a [1]"+ this.Sk_keys.get(1));
         }
     // TODO
     // store the keys in file (id, sk, pk, reA-B)
     
     
     
     }        
         
    
    //
    //
    //
 public void init(Element sk_a, Element pk_a) {
     
     System.out.println("||||||||||| First Level Encryption  ||||||||||||||");
     System.out.println("|||||||||||      Initilization      ||||||||||||||");
     System.out.println("pk_a :" + pk_a);
     System.out.println("sk_a :" + sk_a);
     
     PairingParameters curveParams = PairingFactory.getPairingParameters("a_181_603.properties");
      this.pairing = PairingFactory.getPairing(curveParams);
      this.g1 = this.pairing.getG1();
      this.gt = this.pairing.getGT();
      this.zr = this.pairing.getZr();
      this.k= this.zr.newRandomElement().getImmutable();
      this.g = this.g1.newRandomElement().getImmutable();
      //this.gpre = this.g.pow();
      this.g_k = this.gpre.powZn(k).getImmutable();
      this.z_k = this.pairing.pairing(g, g_k).getImmutable();
      this.z_a_k = this.pairing.pairing(gpre.powZn(sk_a), g_k).getImmutable();
      
     
     System.out.println("pk_a :" + pk_a);
     System.out.println("sk_a :" + sk_a);
    /* System.out.println("g1   :" + g1);
     System.out.println("gt   :" + gt);
     System.out.println("zr   :" + zr);
    */
     System.out.println("k    :" + this.k);
     System.out.println("g    :" + this.g);
   //  System.out.println("gpre :" + this.gpre);
     System.out.println("Z^k  :" + this.z_k);
     System.out.println("Z^ak :" + this.z_a_k);
     
     

    }        
     
    //
    //
    //
 public Element SecKeyGeneration() {
    
        System.out.println("||||||||||| First Level Encryption  ||||||||||||||");
        System.out.println("||||||||||| Key Generation Phase ||||||||||||||");
        // sk_a is secret key 
         return (this.sk_a = this.zr.newRandomElement().getImmutable());
         }
     
      public Element InvDBOwnerSecKey() {
           return (this.insk_a = this.sk_a.invert());
         }
      
       public Element InvUserSecKey(int UserId) {
           return (this.insk_a = this.Sk_keys.get(UserId).invert());
         }
         //  

    
    //
    //
    //
 public void GenerateMessage() {
     
     System.out.println("|||||||||||     Generate message    ||||||||||||||");
     this.message = this.gt.newRandomElement();
     System.out.println("Message :" + this.message);
         }
    
     //
    //
    //
 public Element GenMessage() {
     
     System.out.println("|||||||||||     Generate message    ||||||||||||||");
     this.message = this.gt.newRandomElement();
     System.out.println("Message :" + this.message);

     return (this.message);
      }
    
     //
     //
     //
    // public Element ReEncryption(Element pk_b){
 public Element ReEncryption(int UserId){
     
     System.out.println("pk_b" + Pk_keys.get(UserId));
     
      //Inv_sk_a = this.Sk_keys.get(0).duplicate().invert();  // invert sk_a = 1/sk_a
    return (this.rkA_B = Pk_keys.get(UserId).powZn(this.InvDBOwnerSecKey()).getImmutable());
       }
      
      //
      //
      //
     //
      //  pick random k, compute Z^k
      //
 public void InitParam_FL_SL_Enc_Dec(){

     PairingParameters curveParams = PairingFactory.getPairingParameters("a_181_603.properties");
      this.pairing = PairingFactory.getPairing(curveParams);
      this.g1 = this.pairing.getG1();
      this.gt = this.pairing.getGT();
      this.zr = this.pairing.getZr();
     // this.k= this.zr.newRandomElement();
      this.g = this.g1.newRandomElement();
     // this.gpre = this.g.pow();
      
     this.k= this.zr.newRandomElement();
      this.g_k = this.gpre.powZn(k);
      this.z_k = this.pairing.pairing(g, g_k);
      System.out.println("k    :" + this.k);
      System.out.println("Z^k  :" + this.z_k);
    
       }      
 public ArrayList<Element> FirstLevelEnc(int UserId){

       Element temp, c1,c2;
       ArrayList<Element> Cipher = new ArrayList<Element>();
    
       this.InitParam_FL_SL_Enc_Dec();
       temp = this.gpre.powZn(this.Sk_keys.get(UserId));
       this.z_a_k = this.pairing.pairing(temp,this.g_k);  // z_a_k = c1
       c1 = this.z_a_k;
       this.message = this.GenMessage();
       c2 = this.message.duplicate().mul(this.z_k);
       
       Cipher.add(c1);
       Cipher.add(c2);
       System.out.println("||||||||||| First-Level Encryption  ||||||||||||||");

       System.out.println("k    :" + this.k);
       System.out.println("Z^k  :" + this.z_k);
       System.out.println("Z^ak :" + this.z_a_k);
       System.out.println("message : " + this.message);
       System.out.println("c1      : " + c1);
       System.out.println("c2      : " + c2);
       // Compute c2 = plaintext * Z^k
       
       //  check this yousef , the same as m
       // To check whether first level encryption works or not
       //   this.FirstLevelDec(UserId, c1 ,c2);
       return (Cipher);
      
      }
      
      //
      //
      //
 public void FirstLevelDec(int UserId, Element alpha, Element beta, boolean proxy){

       Element temp, inv_alpha, dec_message;
       
       System.out.println("||||||||||| First-Level Decryption  ||||||||||||||");

       System.out.println("k    :" + this.k);
       System.out.println("Z^k  :" + this.z_k);
 
       if (!proxy) {
          System.out.println("Z^ka :" + this.z_a_k);
          System.out.println("message : " + this.message);
  
       }
       System.out.println("alpha   :"+ alpha);
       System.out.println("beta    : "+ beta);
       temp = this.InvUserSecKey(UserId);
       inv_alpha = alpha.powZn(temp);
       dec_message = beta.div(inv_alpha);
     
       System.out.println("dec message : "+dec_message);
              
      }
 
 
  public ArrayList<Element> FirstLevelEncString(int UserId, Element e1,Element e2,Element e3 ){

       Element temp, c1,c2_1,c2_2, c2_3;
       ArrayList<Element> Cipher = new ArrayList<Element>();
    
       this.InitParam_FL_SL_Enc_Dec();
       temp = this.gpre.powZn(this.Sk_keys.get(UserId));
       this.z_a_k = this.pairing.pairing(temp,this.g_k);  // z_a_k = c1
       c1 = this.z_a_k;
       
       c2_1 = e1.duplicate().mul(this.z_k);
       c2_2 = e2.duplicate().mul(this.z_k);
       c2_3 = e3.duplicate().mul(this.z_k);
      
               
       Cipher.add(c1);
       Cipher.add(c2_1);
       Cipher.add(c2_2);
       Cipher.add(c2_3);
     
       System.out.println("||||||||||| First-Level Encryption  ||||||||||||||");

       System.out.println("k    :" + this.k);
       System.out.println("Z^k  :" + this.z_k);
       System.out.println("Z^ak :" + this.z_a_k);
       
       
       return (Cipher);
      
      }
  
public  ArrayList<Element> FirstLevelDecString(int UserId, Element c1, Element e1,Element e2,Element e3, boolean proxy ){ // later change e1:4 in one array

     Element temp,inv_alpha, dec_message1,dec_message2,dec_message3;
     
     ArrayList<Element> Cipher = new ArrayList<Element>();
             this.InitParam_FL_SL_Enc_Dec();
 
       System.out.println("||||||||||| First-Level Decryption String ||||||||||||||");
       System.out.println("alpha [c1]  : " + c1);
       System.out.println("beta1 c[2_1]: " + e1);
       System.out.println("beta2 c[2_2]: " + e2);
       System.out.println("beta3 c[2_3]: " + e3);

       System.out.println("k    : " + this.k);
       System.out.println("Z^k  : " + this.z_k);
 
       if (!proxy) {
          System.out.println("Z^ka : " + this.z_a_k);
         // System.out.println("message : " + this.message);
  
       }
      System.out.println("test");
       temp = this.Sk_keys.get(UserId).invert();
       System.out.println("sk ssssssssssss2222222222222"+Sk_keys.get(UserId));
      // store the keys in file
      // this.InvUserSecKey(UserId);
       inv_alpha = c1.powZn(temp);
      
       dec_message1 = e1.div(inv_alpha);
       System.out.println("dec message : " + dec_message1);
       dec_message2 = e2.div(inv_alpha);
       System.out.println("dec message : " + dec_message2);
       dec_message3 = e3.div(inv_alpha);
       System.out.println("dec message : " + dec_message3);
            
       Cipher.add(dec_message1);
       Cipher.add(dec_message2);
       Cipher.add(dec_message3);
      
      return (Cipher); 
    
      }  
       
 public ArrayList<Element> SecondLevelEnc(int UserId){

       Element c1,c2;
       ArrayList<Element> Cipher = new ArrayList<Element>();

       this.InitParam_FL_SL_Enc_Dec();
       c1 = this.Pk_keys.get(UserId).powZn(this.k);
       this.message = this.GenMessage(); 
       c2 = this.message.duplicate().mul(this.z_k);
       System.out.println("||||||||||| Second-Level Encryption  ||||||||||||||");
       Cipher.add(c1);
       Cipher.add(c2);
       
       System.out.println("k    :" + this.k);
       System.out.println("message : " + this.message);
       System.out.println("c1      : " + c1);
       System.out.println("c2      : " + c2);
       // Compute c2 = plaintext * Z^k
   
       return (Cipher);
      }
        
        
 public void SecondLevelDec(int UserId, Element alpha, Element beta){

       Element temp, e_alpha, inv_alpha, dec_message;
       System.out.println("||||||||||| Second-Level Decryption  ||||||||||||||");

       System.out.println("k    :" + this.k);
       System.out.println("Z^k  :" + this.z_k);
       System.out.println("Z^ka :" + this.z_a_k);
       System.out.println("message : " + this.message);
       System.out.println("alpha   :"+ alpha);
       System.out.println("beta    : "+ beta);
       
       temp = this.InvUserSecKey(UserId);
       e_alpha = this.pairing.pairing(alpha, this.g);
       
       inv_alpha = e_alpha.powZn(temp);
       dec_message = beta.div(inv_alpha);
     
       System.out.println("dec message : "+dec_message);
      
        }
        
        // Any body can change a second level cipher text for A
       // a cipher text for B with rk_A->B = g^b/a.
       
       // given ca=(g^ak = z_a_k2,mZ^k)
       // rk_A_B=reA_B,    m = m2
      // compute:
       // Z^bk=e(g^ak,[g^b/a]=reA_B)
        // UserId here is the target user/consumer, and NOT DATABASE OWNER
        
 public ArrayList<Element> ProxyReEncryption(int UserId, Element alpha, Element beta){
  
        Element temp;
        ArrayList<Element> Cipher_pr = new ArrayList<Element>();
       
        
       // this.ReEncryption(UserId);   // generate rkA_B
        System.out.println("k = "+this.k);
        
        this.rkA_B = this.Pk_keys.get(UserId).powZn(this.insk_a).getImmutable();
        System.out.println("rkA_B :" + this.rkA_B);
        System.out.println("pk    :" + this.Pk_keys.get(UserId));

        temp = this.pairing.pairing(alpha,this.rkA_B).getImmutable();
        Cipher_pr.add(temp);
        Cipher_pr.add(beta);
        
        System.out.println("||||||||||| Re-Encryption Phase  ||||||||||||||");
        System.out.println("rkA_B :" + this.rkA_B);
        System.out.println("form : 2nd Level Encryption");
        System.out.println("c1      :" + alpha);
        System.out.println("c2      : "+ beta);
        System.out.println("to   : 1st Level Encryption");
        System.out.println("c1      : " + temp);
        System.out.println("c2      : " + beta);
        
        return (Cipher_pr);
        }      
 
 
 
 // Data base owner encrypts data base to 2nd level encryption and store it in file
  // TO DO
 public ArrayList<Element> SecondLevelEncByDBOwner(Element e1, Element e2, Element e3){

       Element c1,c2_1, c2_2, c2_3;
 //      fileOutputStream = new FileOutputStream("bls_keypair.params");

       ArrayList<Element> Cipher = new ArrayList<Element>();
      
       this.InitParam_FL_SL_Enc_Dec();
       c1 = this.Pk_keys.get(0).powZn(this.k);
       c2_1 = e1.duplicate().mul(this.z_k);
       c2_2 = e2.duplicate().mul(this.z_k);
       c2_3 = e3.duplicate().mul(this.z_k);
       
       System.out.println("||||||||||| Second-Level Encryption for Multiple messages ||||||||||||||");
       Cipher.add(c1);   // alpha = c1 is the same for each user
       Cipher.add(c2_1); // c2 = beta, is different as long as the message is different
       Cipher.add(c2_2);
       Cipher.add(c2_3);
    
       System.out.println("k    :" + this.k);
       System.out.println("message : " + e1);
       System.out.println("message : " + e2);
       System.out.println("message : " + e3);
              
       System.out.println("c1=alpha: " + c1);
       System.out.println("c2_1    : " + c2_1);
       System.out.println("c2_2    : " + c2_2);
       System.out.println("c2_3    : " + c2_3);
    
       // Compute c2 = plaintext * Z^k
   
       return (Cipher);
      }
 

 
 public ArrayList<Element> ProxyReEncryptionString(int UserId, Element [] c){
  
        Element temp;
        ArrayList<Element> Cipher_pr = new ArrayList<Element>();
        int i=0;
        
       // call initall pahase, I guss
       // this.ReEncryption(UserId);   // generate rkA_B
        System.out.println("k = "+this.k);
        
        this.rkA_B = this.Pk_keys.get(UserId).powZn(this.insk_a).getImmutable();
        System.out.println("rkA_B :" + this.rkA_B);
        System.out.println("pk    :" + this.Pk_keys.get(UserId));
       
        System.out.println("||||||||||| Re-Encryption Phase  ||||||||||||||");
        System.out.println("rkA_B :" + this.rkA_B);
        System.out.println("form : 2nd Level Encryption");
        
        System.out.println("c1[" + i + "]: " + c[i]);

         for (i=1;i<c.length;i++){
           System.out.println("c2[" + i + "]: " + c[i]);   
        }
        
        System.out.println("to   : 1st Level Encryption");
        int j=0;
        temp = this.pairing.pairing(c[0],this.rkA_B).getImmutable();
        Cipher_pr.add(temp);
                   System.out.println("c1[" + i + "]: " + temp);
      
        for (i=0;i<c.length;++i){
           Cipher_pr.add(c[i]);
           System.out.println("beta[" + i + "]: " + c[i]);
          }
        return (Cipher_pr);
        }      
   
    public  ArrayList<Element> FirstLevelDecStringP(int UserId, Element [] c){ // later change e1:4 in one array

     Element temp,inv_alpha;
     Element dec_message;
     //1,dec_message2,dec_message3;
     ArrayList<Element> Cipher = new ArrayList<Element>();
     int i=0;
     
      // this.InitParam_FL_SL_Enc_Dec();
       System.out.println("k    : " + this.k);
       System.out.println("Z^k  : " + this.z_k);
       
       System.out.println("c1[" + 0 + "]: " + c[0]);
       temp = this.InvUserSecKey(UserId);
       inv_alpha = c[0].powZn(temp);

       for (i=1;i<c.length-1;i++){
           System.out.println("encrypted message ");   
           System.out.println("c2[" + i + "]: " + c[i]);   
           dec_message= c[i].div(inv_alpha);
           
           System.out.println("decrypted message ");   
           System.out.println("dec message : " + dec_message);
      Cipher.add(dec_message);     

       }
      return (Cipher); 
    
      } 
 
  
 
}
