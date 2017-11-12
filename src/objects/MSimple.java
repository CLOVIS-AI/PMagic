/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objects;

import keys.Key;

/**
 *
 * @author CLOVIS
 */
public interface MSimple {
    
    /**
     * Performs an action on mouse click.<br/><br/>
     * 
     * <b>Note about the use of {@link MWindow} objects : </b><br/>
     * {@link MWindow} objects only call this method if the mouse click is inside of this object ;
     * meaning that clicks outside of the object won't be notified.<br/>
     * ({@link #collides(float, float) } is used to decide wether the click is inside the object or not).
     * @see #onMouseReleased() 
     */
    public void onMousePressed();
    
    /**
     * Performs an action on mouse releasing.<br/><br/>
     * 
     * <b>Note about the use of {@link MWindow} objects : </b><br/>
     * {@link MWindow} objects only call this method if the mouse release occurs inside of this object ;
     * meaning that releases outside of the object won't be notified.<br/>
     * ({@link #collides(float, float) } is used to decide wether the release is inside the object or not).
     * @see #onMousePressed() 
     */
    public void onMouseReleased();
    
    // ***************************** KEY EVENTS ********************************
    
    /**
     * Performs an action on key pressing.
     * @param k the key
     * @see #onKeyReleased(keys.Key)
     * @see #onKeyHold(keys.Key) 
     */
    public void onKeyPressed(Key k);
    
    /**
     * Performs an action on key holding.
     * @param k the key
     * @see #onKeyPressed(keys.Key) 
     * @see #onKeyReleased(keys.Key) 
     */
    public void onKeyHold(Key k);
    
    /**
     * Performs an action on key releasing.
     * @param k the key
     * @see #onKeyPressed(keys.Key) 
     * @see #onKeyHold(keys.Key) 
     */
    public void onKeyReleased(Key k);
}
