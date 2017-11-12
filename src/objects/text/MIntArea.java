/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objects.text;

import keys.Key;
import logger.Task;
import processing.core.PGraphics;
import util.Primitive;
import util.TColor;

/**
 *
 * @author CLOVIS
 */
public class MIntArea extends MWritable {

    private String content;
    
    private boolean canNegative;
    private boolean isPositive;
    
    /**
     * Creates a writable object filled with an integer (int).
     * @param pX X coordinate of left-top corner
     * @param pY Y coordinate of left-top corner
     * @param sX width of the object
     * @param sY height of the object
     * @param write <b>true</b> if this object is writable, <b>false</b> if it's locked (use {@link #allowWriting()} and {@link #disallowWriting()} to change this behavior later)
     * @param backC color of the background
     * @param textC color of the text
     * @param defContent default number
     * @param allowNegative can this number be negative ? (defines the behavior to the keys '-' and '+')
     */
    public MIntArea(float pX, float pY, float sX, float sY, boolean write, int defContent, TColor backC, TColor textC, boolean allowNegative) {
        super(pX, pY, sX, sY, write, backC, textC);
        canNegative = allowNegative;
        isPositive = defContent >= 0;
        content = String.valueOf(defContent >= 0 ? defContent : -defContent);
    }

    @Override
    public Primitive getContent() {
        Primitive p;
        try{
            p = new Primitive(new Integer((isPositive ? "" : "-") + content));
        }catch(NumberFormatException e){
            p = new Primitive(0l);
        }
        return p;
    }
    
    public void setContent(int content){
        this.content = ""+content;
    }
    
    @Override
    public void onKeyPressed(Key k){
        Task.info("MIntArea", "Pressed key " + k.toString());
        if(k.isNumeric() && content.length() < 10){
            Task.info("Writing ...");
            content += k.getInfos().charAt(0);
            Task.info(content);
        }else{
            Task.info("Special characters ...");
            switch(k.getType()){
                case CHAR:
                    switch (k.getInfos()) {
                        case "-":
                            isPositive = false;
                            break;
                        case "+":
                            isPositive = true;
                            break;
                    }
                    break;
                case BACKSPACE:
                    if(content.length() > 0)
                        content = content.substring(0, content.length()-1);
                    break;
                case DELETE:
                    content = "";
                    break;
                case ENTER:
                    onMousePressed();
                    break;
            }
        }
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
        g.text("["+
                (isPositive ? "" : "-")+
                (content.length() > 0 ? content : "0")+
                (content.length() < 10 ? "|" : "]"), 
                getPosX()+2, 
                getPosY()+12, 
                getSizeX()-2, 
                getSizeY()-2);
    }
    
}
