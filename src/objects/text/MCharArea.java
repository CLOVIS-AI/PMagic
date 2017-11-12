/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objects.text;

import util.Primitive;
import util.TColor;

/**
 * 
 * @author CLOVIS
 */
public class MCharArea extends MStringArea {

    public MCharArea(float pX, float pY, float sX, float sY, boolean write, String defContent, TColor backC, TColor textC) {
        super(pX, pY, sX, sY, write, defContent, backC, textC, 1);
    }
    
    @Override
    public Primitive getContent(){
        return new Primitive(super.getContent().getString().charAt(0));
    }
    
}
