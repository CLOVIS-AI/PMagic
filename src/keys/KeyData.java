/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package keys;

import objects.MObject;
import objects.MSimple;

/**
 * Data about a key
 * @author CLOVIS
 */
public class KeyData {
    
    private final boolean hold;
    private boolean pressed;
    private MSimple owner;
    
    /**
     * Creates a KeyData object.
     * @param h <b>true</b> if hold down, <b>false</b> if impulsion only.
     * @param own target of the key
     */
    public KeyData(boolean h, MSimple own){
        hold = h;
        owner = own;
        pressed = false;
    }
    
    /**
     * Returns the hold mode.
     * @return <b>true</b> if this key works by spamming
     */
    public boolean isHoldMode(){ return hold; }
    
    /**
     * Sets the owner.
     * @param o the new owner
     */
    public void setOwner(MObject o){ owner = o; }
    
    /**
     * Gets the owner.
     * @return the owner
     */
    public MSimple getOwner(){ return owner; }
    
    /**
     * Press this key
     */
    public void press(){ pressed = true; }
    
    /**
     * Release this key
     */
    public void release(){ pressed = false; }
    
    /**
     * Is this key pressed ?
     * @return <b>true</b> if this key is pressed
     */
    public boolean isPressed(){ return pressed; }
}
