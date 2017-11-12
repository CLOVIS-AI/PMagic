/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objects.text;

import keys.Key;
import processing.core.PGraphics;
import util.Primitive;
import util.TColor;

/**
 * A String writable text area.
 * @author String
 */
public class MStringArea extends MWritable {

    private String content;
    private int maxLength;
    
    private boolean upperCase;
    
    public MStringArea(float pX, float pY, float sX, float sY, boolean write, String defContent, TColor backC, TColor textC, int mxlength) {
        super(pX, pY, sX, sY, write, backC, textC);
        if(defContent == null)
            throw new IllegalArgumentException("'defContent' should not be null.");
        content = defContent;
        upperCase = true;
        maxLength = mxlength;
    }
    
    @Override
    public void onKeyPressed(Key k){
        if(k.isText() && content.length() < maxLength){
            if(upperCase){  content += k.getInfos().toUpperCase().charAt(0); }
            else{           content += k.getInfos().toLowerCase().charAt(0); }
            upperCase = false;
        }else{
            switch(k.getType()){
                case SHIFT:
                    upperCase = true;
                    break;
                case BACKSPACE:
                    if(content.length() > 0)
                        content = content.substring(0, content.length()-1);
                    break;
                case DELETE:
                    content = "";
                    break;
            }
        }
    }

    @Override
    public Primitive getContent() {
        return new Primitive(content);
    }

    @Override
    public void draw(PGraphics g) {
        g.stroke(0);
        
        TColor back = getBackgroundColor();
        g.fill(back.t(), back.u(), back.v(), back.a());
        g.rect(getPosX(), getPosY(), getSizeX(), getSizeY());
        
        TColor t = getTextColor();
        g.fill(t.t(), t.u(), t.v(), t.a());
        g.textSize(12);
        g.text("["+content+(content.length() < maxLength ? "|" : "]"), getPosX()+2, getPosY()+12, getSizeX()-2, getSizeY()-2);
    }
    
    
    
}
