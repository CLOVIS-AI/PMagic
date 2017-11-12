/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objects;

import java.util.ArrayList;
import keys.Key;
import logger.Task;
import processing.core.PGraphics;

/**
 * A container
 * @author CLOVIS
 */
public class MContainer extends MObject {
    
    public static final float OFFSET = 10;
    
    private final ArrayList<MObject> objects;
    private MObject focus;

    public MContainer(float pX, float pY, float sX, float sY) {
        super(pX, pY, sX, sY);
        focus = null;
        objects = new ArrayList<>();
    }
    
    @Override
    public void draw(PGraphics g) {
        for(int i = 0; i < objects.size(); i++){
            objects.get(i).draw(g);
            if(!objects.get(i).isAlive()){
                objects.remove(objects.get(i));
                i--;
            }
        }
    }
    
    @Override
    public final boolean collides(float X, float Y){
        if(!isFocus() && super.collides(X, Y)){
            super.beginFocus();
        }else if(isFocus() && !super.collides(X, Y)){
            super.endFocus();
        }
        for (int i = 0; i < objects.size(); i++) {
            MObject o = objects.get(i);
            if (o.collides(X, Y)) {
                swapFocus(o);
                return true;
            }
        }
        swapFocus(null);
        return false;
    }
    
    @Override
    public void onMousePressed(){
        if(focus != null)
            focus.onMousePressed(); 
    }
    
    @Override
    public void onKeyPressed(Key k){
        Task.info("MContainer", "Pressed key " + k.toString());
        if(focus != null)
            focus.onKeyPressed(k); 
    }
    
    @Override
    public void onKeyReleased(Key k){
        if(focus != null)
            focus.onKeyReleased(k);
    }
    
    @Override
    public void onEndFocus(){
        swapFocus(null);
    }
    
    /**
     * Swaps the focus.
     * @param nf new object to be focused (null if none).
     */
    private void swapFocus(MObject nf){
        if(focus == nf)     return;
        if(focus != null)
            focus.endFocus();
        if(nf == null){
            focus = null;
        }else{
            focus = nf;
            nf.beginFocus();
        }
    }
    
    /**
     * Adds an object to this container.
     * @param obj the object you want to add
     */
    public final void addObject(MObject obj){
        Task.begin("Adding new MObject to this container.");
        objects.add(obj);
        Task.end("MObject added.");
    }
    
    /**
     * Get the focused object.
     * @return The focused object. If none, returns <code>null</code>.
     */
    public final MObject getFocus(){
        return focus;
    }
    
    /**
     * Get the content of this container.
     * @return An {@link ArrayList } of all the objects contained by this container.
     */
    public final ArrayList<MObject> getContent(){
        return objects;
    }
    
}
