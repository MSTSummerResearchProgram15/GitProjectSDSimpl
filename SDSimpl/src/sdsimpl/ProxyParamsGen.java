/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sdsimpl;

import it.unisa.dia.gas.jpbc.CurveGenerator;
import it.unisa.dia.gas.jpbc.Element;
import it.unisa.dia.gas.jpbc.ElementPowPreProcessing;
import it.unisa.dia.gas.jpbc.Field;
import it.unisa.dia.gas.jpbc.Pairing;
import it.unisa.dia.gas.plaf.jpbc.pairing.CurveParams;
import it.unisa.dia.gas.plaf.jpbc.pairing.PairingFactory;
import it.unisa.dia.gas.plaf.jpbc.pairing.a.TypeACurveGenerator;


public class ProxyParamsGen {

    private CurveParams curveParams;
    private Pairing pairing;
    
    public void initialize(CurveParams curveParams){
        
        this.curveParams = curveParams;      
        this.pairing = PairingFactory.getPairing(curveParams);
    }
    
    // user pre exist curve parameters
    public void init(){
        
        //this.curveParams = new CurveParams().load("src"+File.pathSeparator+"sdsimpl"+File.pathSeparatorChar+"param"+File.pathSeparatorChar+"a_181_603.properties ");      
        this.curveParams = new CurveParams().load("a_181_603.properties");      
        this.pairing = PairingFactory.getPairing(curveParams);
    }
    
    //ToDo:
    // I'll add another way to generate the curve 
    
    public void initCurveTypeA(){
    
        // Init the generator...
        int rBits = 160;
        int qBits = 512;
        CurveGenerator curveGenerator = new TypeACurveGenerator(rBits, qBits);      
    }
    
    ///
    
    public ProxyParams genParams(){
        
        ProxyParams params = new ProxyParams(this.curveParams);
         params.setPairing(this.pairing);
        
         Field g1 = this.pairing.getG1();
         params.setG1(g1);
         
         Field gt = this.pairing.getGT();
         params.setGT(gt);
         
         Field zr = this.pairing.getZr();
         params.setZr(zr);
         
         Element g = g1.newRandomElement().getImmutable();
         params.setg(g);
           
         ElementPowPreProcessing gpre = g.pow();
         params.setgpre(gpre);
         
        /* Element k = zr.newRandomElement().getImmutable();
         params.setk(k);
         
         Element g_k = gpre.powZn(k);
         params.setg_k(g_k);
         
         params.setz_k(this.pairing.pairing(g, g_k));
         */
         
        return params;
    }
    
    
    
}
    