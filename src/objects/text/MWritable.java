/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objects.text;

import objects.MObject;
import util.Primitive;
import util.TColor;

/**
 *
 * @author CLOVIS
 */
public abstract class MWritable extends MObject {

    private boolean writable;
    
    private TColor backgroundColor, textColor;
    
    /**
     * Creates a writable object
     * @param pX X coordinate of left-top corner
     * @param pY Y coordinate of left-top corner
     * @param sX width of the object
     * @param sY height of the object
     * @param write <b>true</b> if this object is writable, <b>false</b> if it's locked (use {@link #allowWriting()} and {@link #disallowWriting()} to change this behavior later)
     * @param backC color of the background
     * @param textC color of the text
     */
    public MWritable(float pX, float pY, float sX, float sY, boolean write, TColor backC, TColor textC) {
        super(pX, pY, sX, sY);
        writable = write;
        backgroundColor = backC;
        textColor = textC;
    }
    
    /**
     * Get the background color.
     * @return The background color
     */
    public TColor getBackgroundColor(){
        return backgroundColor;
    }
    
    /**
     * Get the text color.
     * @return The text color
     */
    public TColor getTextColor(){
        return textColor;
    }
    
    @Override
    public final boolean isWritable(){
        return writable;
    }
    
    /**
     * Unlocks this object : allows writing.
     */
    public final void allowWriting(){
        writable = true;
    }
    
    /**
     * Locks this object : disallows writing.
     */
    public final void disallowWriting(){
        writable = false;
    }
    
    /**
     * Get the content of this object as a Primitive object.
     * @return The content of this object.
     */
    abstract public Primitive getContent();
}
