/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objects;

import keys.Key;
import processing.core.PGraphics;

/**
 * A class for graphic objects, defining basic rules for implementations.
 * @author CLOVIS
 */
public abstract class MObject implements MSimple {
    
    private float posX, posY;
    private float sizeX, sizeY;
    
    private boolean isFocus;
    
    /**
     * Creates a new object.
     * @param pX X coordinate of the top-left corner ({@link #setPosX(float) Modify it})
     * @param pY Y coordinate of the top-left corner ({@link #setPosY(float) Modify it})
     * @param sX width ({@link #setSizeX(float) Modify it})
     * @param sY height ({@link #setSizeY(float) Modify it}) 
     */
    public MObject(float pX, float pY, float sX, float sY){
        setPosX(pX);
        setPosY(pY);
        setSizeX(sX);
        setSizeY(sY);
    }
    
    // ***************************** DRAWING ***********************************
    
    /**
     * Draws this object on a specified surface.
     * @param g where to draw this object
     */
    public abstract void draw(PGraphics g);
    
    /**
     * Checks if a point is inside this {@link MObject}.
     * @param X X coordinate
     * @param Y Y coordinate
     * @return <b>true</b> if colliding.
     */
    public boolean collides(float X, float Y){
        return X >= posX && X <= posX+sizeX && Y >= posY && Y <= posY+sizeY;
    }
    
    // ***************************** FOCUS *************************************
    
    /**
     * Tell this object that it's being focused.<br/><br/>
     * To perform an action on focus beginning, override the empty method {@link #onBeginFocus() }, which is called right after focus has been updated.
     * @see #endFocus() 
     */
    public final void beginFocus(){
        isFocus = true;
        onBeginFocus();
    }
    
    /**
     * Tell this object that it's being unfocused.<br/><br/>
     * To perform an action on focus ending, override the empty method {@link #onEndFocus() }, which is called right after focus has been updated.
     * @see #beginFocus() 
     */
    public final void endFocus(){
        isFocus = false;
        onEndFocus();
    }
    
    /**
     * Is this object focused ?<br/><br/>
     * This method works according to the calls of {@link #beginFocus() } and {@link #endFocus() }. If these methods are not called, or called at wrong times, {@link #isFocus() } will return wrong results.
     * @return <b>true</b> if this object is focused
     */
    public final boolean isFocus(){
        return isFocus;
    }
    
    /**
     * This method is called at the end of {@link #beginFocus() } to ease the performing of actions on this event. The original implementation doesn't do anything.
     * @see #onEndFocus() 
     */
    public void onBeginFocus(){};
    
    /**
     * This method is called at the end of {@link #endFocus() } to ease the performing of actions of this event. The original implementation doesn't do anything.
     * @see #onBeginFocus() 
     */
    public void onEndFocus(){};
    
    // ******************************* MOUSE EVENTS ****************************
    
    @Override
    public void onMousePressed(){}
    
    @Override
    public void onMouseReleased(){}
    
    // ***************************** KEY EVENTS ********************************
    
    @Override
    public void onKeyPressed(Key k){}
    
    @Override
    public void onKeyHold(Key k){}
    
    @Override
    public void onKeyReleased(Key k){}
    
    // ************************** WRITING **************************************
    
    /**
     * Is this object writable ?
     * @return <b>true</b> if this object is writable. (<b>false</b> by default, see {@link MWritable} for more informations on writable objects)
     */
    public boolean isWritable(){ return false; }
    
    // ******************************* ALIVE ***********************************
    
    /**
     * Is this object alive ?<br/><br/>
     * The method is used to check for object which self-destruct after some time or on an event. The default implementation always returns <b>true</b>.
     * @return <b>true</b> if this object is alive.
     */
    public boolean isAlive(){ return true; }
    
    // ***************************** POSITION **********************************
    
    /**
     * Sets the position of this object.
     * @param pX X coordinate of the top-left corner.
     * @see #setPosY(float) to set the Y coordinate
     */
    public final void setPosX(float pX){ posX = pX; }
    
    /**
     * Sets the position of this object.
     * @param pY Y coorinate of the top-left corner.
     * @see #setPosX(float) to set the X coordinate
     */
    public final void setPosY(float pY){ posY = pY; }
    
    /**
     * Gets the position of this object.
     * @return The X coordinate of the top-left corner.
     * @see #getPosY() to get the Y coordinate
     */
    public final float getPosX(){ return posX; }
    
    /**
     * Gets the position of this object.
     * @return The Y coordinate of the top-left corner.
     * @see #getPosX() to get the X coordinate
     */
    public final float getPosY(){ return posY; }
    
    /**
     * Same as {@link #getPosX() } but casted to an <b>int</b> for convenience.
     * @return The X coordinate of the top-left corner.
     * @see #getIntPosY() to get the Y coordinate as an int
     */
    public final int getIntPosX(){ return (int)posX; }
    
    /**
     * Same as {@link #getPosY() } but casted to an <b>int</b> for convenience.
     * @return The Y coordinate of the top-left corner.
     * @see #getIntPosX() to get the X coordinate as an int
     */
    public final int getIntPosY(){ return (int)posY; }
    
    /**
     * Sets the width of this object.
     * @param sX new width
     * @throws IllegalArgumentException if the new width is negative. In this case, the width is left unaltered.
     * @see #setSizeY(float) to set the height
     */
    public final void setSizeX(float sX) throws IllegalArgumentException {
        if(sX < 0) 
            throw new IllegalArgumentException("Size should be bigger than 0 : "+sX); 
        sizeX = sX; 
    }
    
    /**
     * Sets the height of this object.
     * @param sY new height
     * @throws IllegalArgumentException if the new height is negative. In this case, the height is left unaltered.
     * @see #setSizeX(float) to set the width
     */
    public final void setSizeY(float sY) throws IllegalArgumentException { 
        if(sY < 0) 
            throw new IllegalArgumentException("Size should be bigger than 0 : "+sY); 
        sizeY = sY; 
    }
    
    /**
     * Gets the width of this object.
     * @return The width of this object.
     * @see #getSizeY() Get the height
     * @see #setSizeX(float) Set the width
     */
    public final float getSizeX(){ return sizeX; }
    
    /**
     * Gets the height of this object.
     * @return The height of this object.
     * @see #getSizeX() Get the width
     * @see #setSizeY(float) Set the height
     */
    public final float getSizeY(){ return sizeY; }
    
    /**
     * Same as {@link #getSizeX() } but casted to an <b>int</b> for convenience.
     * @return The width
     * @see #getIntSizeY() Get the height as an int
     */
    public final int getIntSizeX(){ return (int)sizeX; }
    
    /**
     * Same as {@link #getSizeY() } but casted to an <b>int</b> for convenience.
     * @return The height
     * @see #getIntSizeX() Get the width as an int
     */
    public final int getIntSizeY(){ return (int)sizeY; }
}
