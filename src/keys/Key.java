/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package keys;

import java.util.Objects;
import processing.core.PApplet;

/**
 * Represents a key from the keyboard.
 * @author CLOVIS
 * @version 2.2
 * @see KeyType
 */
public class Key {
    private final KeyType keyType;
    private final String moreInfos;
    
    /**
     * Creates a Key using Processing's built-in symbols
     * @param key Processing's key variable
     * @param keyCode Processing's keyCode variable
     * @since 2.0
     */
    public Key(char key, int keyCode){
        switch(key){
            //Simple keys
            case PApplet.ENTER:
            case PApplet.RETURN:          keyType = KeyType.ENTER;     moreInfos = null;      break;
            case PApplet.BACKSPACE:       keyType = KeyType.BACKSPACE; moreInfos = null;      break;
            case PApplet.DELETE:          keyType = KeyType.DELETE;    moreInfos = null;      break;
            case PApplet.ESC:             keyType = KeyType.ESC;       moreInfos = null;      break;
            case PApplet.TAB:             keyType = KeyType.TAB;       moreInfos = null;      break;
            
            //If it's an other key
            case PApplet.CODED:
                switch(keyCode){
                    case PApplet.SHIFT:   keyType = KeyType.SHIFT;     moreInfos = null;      break;
                    case PApplet.ALT:     keyType = KeyType.ALT;       moreInfos = null;      break;
                    case PApplet.CONTROL: keyType = KeyType.CTRL;      moreInfos = null;      break;
                    case PApplet.UP:      keyType = KeyType.ARROW;     moreInfos = "UP";      break;
                    case PApplet.DOWN:    keyType = KeyType.ARROW;     moreInfos = "DOWN";    break;
                    case PApplet.RIGHT:   keyType = KeyType.ARROW;     moreInfos = "RIGHT";   break;
                    case PApplet.LEFT:    keyType = KeyType.ARROW;     moreInfos = "LEFT";    break;
                    default:              keyType = KeyType.OTHER;     moreInfos = Integer.toString(keyCode).toUpperCase();   break;
                }   break;
            
            //If none of the above
            default:                    keyType = KeyType.CHAR;      moreInfos = (new String(new char[]{key})).toUpperCase();
        }
        //System.out.println(toString());
    }
    
    /**
     * Creates a key using a char.
     * @param key a char
     */
    public Key(char key){
        keyType = KeyType.CHAR;      moreInfos = (new String(new char[]{key})).toUpperCase();
    }
    
    /**
     * Is this key a text ?
     * @return <b>true</b> if it is
     */
    public boolean isText(){
        return keyType == KeyType.CHAR;
    }
    
    /**
     * Does this key hold a numerical value ?
     * @return <b>true</b> if it does
     */
    public boolean isNumeric(){
        if(!isText())     return false;
        switch(moreInfos){
            case "0":
            case "1": case "2": case "3":
            case "4": case "5": case "6":
            case "7": case "8": case "9":
                return true;
            default:
                return false;
        }
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 83 * hash + Objects.hashCode(this.keyType);
        hash = 83 * hash + Objects.hashCode(this.moreInfos);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)                    return false;
        if (getClass() != obj.getClass())   return false;
        
        final Key other = (Key) obj;
        if (this.keyType != other.keyType)                      return false;
        if (!Objects.equals(this.moreInfos, other.moreInfos))   return false;
        
        return true;
    }
    
    /**
     * Get the type of the key
     * @return This key's type
     * @since 2.0
     */
    public KeyType getType(){
        return keyType;
    }
    
    /**
     * Get the bonus infos (either a key code, or UP | DOWN |...)
     * @return the info
     * @since 2.0
     */
    public String getInfos(){
        return moreInfos;
    }
    
    /**
     * Returns a String representing this key
     * @return the String
     * @since 2.0
     */
    @Override
    public String toString(){
        return keyType + "_" + moreInfos;
    }
    
    /**
     * Converts an array of characters to an array of the corresponding keys, with same order and same length.
     * @param keys a character array
     * @return A Key array.
     */
    public static Key[] fromCharArrayToKey(char[] keys){
        Key[] ret = new Key[keys.length];
        for(int i = 0; i < keys.length; i++)
            ret[i] = new Key(keys[i]);
        return ret;
    }
}