/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author ymez76
 */

import it.unisa.dia.gas.jpbc.Element;
import it.unisa.dia.gas.jpbc.ElementPowPreProcessing;
import it.unisa.dia.gas.jpbc.Field;
import it.unisa.dia.gas.jpbc.Pairing;


public class User {
    public int userID;
    public Element pk;
    public Element sk;
    public Element reA_B;
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
    
   // User(int userID, Element pk, Element sk, Element reA_B)
    User() {
    
    this.userID = 0;
    this.pk =null;
    this.sk = null;
    this.reA_B = null;
    this.k = null;
    this.g1 = null;
    this.pairing = null;
    
    
    }
    
    User(int userID, Element pk, Element sk, Element reA_B, Element k, Element g1 ,Element pairing ){
    
    this.userID = userID;
    this.pk = pk;
    this.sk = sk;
    this.reA_B = reA_B;
    this.k = k;
    
    }
}
    
