/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import keys.Key;

/**
 * A class that contains all primitive types. Supported types :
 * <ul>
 * <li>byte</li>
 * <li>short</li>
 * <li>int</li>
 * <li>long</li>
 * <li>float</li>
 * <li>double</li>
 * <li>char</li>
 * <li>boolean</li>
 * <li>{@link String}</li>
 * <li>{@link keys.Key}</li>
 * </ul>
 * @author CLOVIS
 */
public class Primitive extends MData {
    
    private final int type;
    
    // ******************************** BYTE ***********************************
    private byte b;
    
    /**
     * Creates a Primitive object containing a byte.
     * @param value 
     */
    public Primitive(byte value){
        b = value;
        type = 0;
    }
    
    /**
     * Gets the byte value.
     * @return byte value, or 0 if not set.
     */
    public byte getByte(){
        return b;
    }
    
    
    // ******************************** SHORT **********************************
    private short s;
    
    /**
     * Creates a Primitive object containing a short.
     * @param value 
     */
    public Primitive(short value){
        s = value;
        type = 1;
    }
    
    /**
     * Gets the short value.
     * @return short value, or 0 if not set.
     */
    public short getShort(){
        return s;
    }
    
    // ******************************** INT ************************************
    private int i;
    
    /**
     * Creates a Primitive object containing an int.
     * @param value 
     */
    public Primitive(int value){
        i = value;
        type = 2;
    }
    
    /**
     * Gets the int value.
     * @return int value, or 0 if not set.
     */
    public int getInt(){
        return i;
    }
    
    // ******************************** LONG ***********************************
    private long l;
    
    /**
     * Creates a Primitive object containing a long.
     * @param value 
     */
    public Primitive(long value){
        l = value;
        type = 3;
    }
    
    /**
     * Gets the long value.
     * @return long value, or 0 if not set.
     */
    public long getLong(){
        return l;
    }
    
    // ******************************* FLOAT ***********************************
    private float f;
    
    /**
     * Creates a Primitive object containing a float.
     * @param value 
     */
    public Primitive(float value){
        f = value;
        type = 4;
    }
    
    /**
     * Gets the float value.
     * @return float value, or 0 if not set.
     */
    public float getFloat(){
        return f;
    }
    
    // ******************************** DOUBLE *********************************
    private double d;
    
    /**
     * Creates a Primitive object containing a double.
     * @param value 
     */
    public Primitive(double value){
        d = value;
        type = 5;
    }
    
    /**
     * Gets the double value.
     * @return double value, or 0 if not set.
     */
    public double getDouble(){
        return d;
    }
    
    // ******************************* BOOLEAN *********************************
    private boolean bb;
    
    /**
     * Creates a Primitive object containing a boolean.
     * @param value 
     */
    public Primitive(boolean value){
        bb = value;
        type = 6;
    }
    
    /**
     * Gets the boolean value.
     * @return boolean value.
     */
    public boolean getBoolean(){
        return bb;
    }
    
    // ******************************** CHAR ***********************************
    private char c;
    
    /**
     * Creates a Primitive object containing a char.
     * @param value 
     */
    public Primitive(char value){
        c = value;
        type = 7;
    }
    
    /**
     * Gets the char value.
     * @return char value, or '?' if not set.
     */
    public char getChar(){
        return type==7 ? c : '?';
    }
    
    // ******************************** STRING *********************************
    private String ss;
    
    /**
     * Creates a Primitive object containing a String.
     * @param value 
     */
    public Primitive(String value){
        ss = value;
        type = 8;
    }
    
    /**
     * Gets the String value.
     * @return String value, or "" if not set.
     */
    public String getString(){
        return type==8 ? ss : "";
    }
    
    // ********************************** KEY **********************************
    private Key k;
    
    /**
     * Creates a Primitive object containing a Key.
     * @param value 
     */
    public Primitive(Key value){
        k = value;
        type = 9;
    }
    
    /**
     * Gets the Key value.
     * @return Key value, or 'CHAR_0' if not set.
     */
    public Key getKey(){
        return type==9 ? k : new Key('0');
    }
    
    // ****************************** TO STRING ********************************
    
    @Override
    public String toString(){
        switch(type){
            case 0: return String.valueOf(b);
            case 1: return String.valueOf(s);
            case 2: return String.valueOf(i);
            case 3: return String.valueOf(l);
            case 4: return String.valueOf(f);
            case 5: return String.valueOf(d);
            case 6: return String.valueOf(bb);
            case 7: return String.valueOf(c);
            case 8: return ss;
            case 9: return getKey().toString();
            default: return "Error.";
        }
    }
}
