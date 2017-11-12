/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objects;

import objects.interact.Interact;
import objects.interact.Reason;
import static processing.core.PApplet.pow;
import static processing.core.PApplet.sqrt;
import processing.core.PGraphics;
import util.Triplet;

/**
 * A simple circle button.
 * @author CLOVIS
 */
public class MCircle extends MObject {
    
    private Triplet<Integer, Integer, Integer> color;
    private Interact inter;
    private float ray;
    
    public MCircle(float pX, float pY, float ray, Triplet<Integer, Integer, Integer> cl, Interact i) {
        super(pX, pY, ray*2, ray*2);
        this.ray = ray;
        color = cl;
        inter = i;
    }

    @Override
    public void draw(PGraphics g) {
        g.fill(color.t(), color.u(), color.v(), isFocus() ? 255 : alphaWhenUnfocused);
        g.stroke(255, 0, 0);
        g.ellipse(getPosX(), getPosY(), getSizeX(), getSizeY());
    }
    
    @Override
    public boolean collides(float x, float y){
        return sqrt(pow(x - getPosX(), 2) + pow(y - getPosY(), 2)) <= ray;
    }
    
    @Override
    public void onMousePressed(){
        inter.actOn(Reason.MOUSE_PRESSED);
    }
    
    @Override
    public void onMouseReleased(){
        inter.actOn(Reason.MOUSE_RELEASED);
    }
    
    // ************************* STATIC ****************************************
    
    public float alphaWhenUnfocused = 175f;
}
