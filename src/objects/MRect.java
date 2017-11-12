/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objects;

import objects.interact.Interact;
import objects.interact.Reason;
import processing.core.PGraphics;
import styles.Color;

/**
 * A rectangular button
 * @author CLOVIS
 */
public class MRect extends MObject {
    
    private Color color;
    private Interact inter;
    
    public MRect(float pX, float pY, float sX, float sY, Color cl, Interact i) {
        super(pX, pY, sX, sY);
        color = cl;
        inter = i;
    }

    @Override
    public void draw(PGraphics g) {
        color.fill(g);
        g.rect(getPosX(), getPosY(), getSizeX(), getSizeY());
    }
    
    @Override
    public void onMousePressed(){
        inter.actOn(Reason.MOUSE_PRESSED);
    }
    
    @Override
    public void onMouseReleased(){
        inter.actOn(Reason.MOUSE_RELEASED);
    }
    
}
