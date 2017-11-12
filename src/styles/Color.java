/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package styles;

import processing.core.PApplet;
import processing.core.PGraphics;

/**
 * A convenient class for colors in Processing
 * @author CLOVIS
 * @version 3
 */
public class Color {
    
    private int value;
    private int red, green, blue, alpha;
    
    /**
     * Creates a clone of the color
     * @param c the source of the clone
     */
    public Color(Color c){
        this.value = c.value();
        alpha = (value >> 24) & 0xFF;
        red =   (value >> 16) & 0xFF;
        green = (value >> 8)  & 0xFF;
        blue =  (value)       & 0xFF;
    }
    
    /**
     * Creates a transparent color with the RGB values
     * @param red the red value [0..255]
     * @param green the green value [0..255]
     * @param blue the blue value [0..255]
     * @param alpha the alpha value [0..255]
     */
    public Color(int red, int green, int blue, int alpha){
        this.red = checkValue(red);
        this.green = checkValue(green);
        this.blue = checkValue(blue);
        this.alpha = checkValue(alpha);
        value = get(red, green, blue, alpha);
    }
    
    /**
     * Creates a grey-scale color
     * @param grey the grey value [0..255]
     */
    public Color(int grey){
        this(grey, grey, grey, 0);
    }
    
    /**
     * Creates a grey-scale transparent color
     * @param grey the grey value [0..255]
     * @param alpha the alpha value [0..255]
     */
    public Color(int grey, int alpha){
        this(grey, grey, grey, alpha);
    }
    
    /**
     * Creates a color with RGB values
     * @param red the red value [0..255]
     * @param green the green value [0..255]
     * @param blue the blue value [0..255]
     */
    public Color(int red, int green, int blue){
        this(red, green, blue, 0);
    }
    
    /**
     * Get the value as an int (identical to {@link #v() }). <b>WARNING.</b> This method is bugged, will be fixed in a future version.
     * @return the value
     * @deprecated
     */
    public int value(){
        return value;
    }
    
    /**
     * Get the value as an int (identical to {@link #value() } but with a shorter name for convenience). <b>WARNING.</b> This method is bugged, will be fixed in a future version. 
     * @return 
     * @deprecated
     */
    public int v(){
        return value;
    }
    
    /** Get the red value as an int within [0..255]
     * @return the red value */
    public int red(){ return red; }
    
    /** Get the green value as an int within [0..255]
     * @return the green value */
    public int green(){ return green; }
    
    /** Get the blue value as an int within [0..255]
     * @return the blue value */
    public int blue(){ return blue; }
    
    /** Get the alpha value as an int within [0..255]
     * @return the alpha value */
    public int alpha(){ return alpha; }
    
    /** Get the red value as an int within [0..255]
     * @return the red value */
    public int r(){ return red; }
    
    /** Get the green value as an int within [0..255]
     * @return the green value */
    public int g(){ return green; }
    
    /** Get the blue value as an int within [0..255]
     * @return the blue value */
    public int b(){ return blue; }
    
    /** Get the alpha value as an int within [0..255]
     * @return the alpha value */
    public int a(){ return alpha; }
    
    /**
     * Shortcut for Processing's fill function.
     * @param g canvas as a PGraphics object
     */
    public void fill(PGraphics g){
        g.fill(red, green, blue, alpha);
    }
    
    /**
     * Shortcut for Processing's fill function.
     * @param g canvas as a PApplet object
     */
    public void fill(PApplet g){
        g.fill(red, green, blue, alpha);
    }
    
    /**
     * Gets the hue value of this color, calculated using the Preucil Cirle.
     * @return this color's hue
     */
    public float hue(){
        float ret;
        if(red >= green && green >= blue){
            ret = (float)(green - blue) / (float)(red - blue);
        }else if(green > red && red >= blue){
            ret = (2 - (float)(red - blue) / (float)(green - blue));
        }else if(green >= blue && blue >= red){
            ret = (2 + (float)(blue - red) / (float)(green - red));
        }else if(blue > green && green > red){
            ret = (4 - (float)(green - red) / (float)(blue - red));
        }else if(blue > red && red >= green){
            ret = (4 + (float)(red - green) / (float)(blue - green));
        }else{
            ret = (6 - (float)(blue - green) / (float)(red - green));
        }
        return ret * 60 * 0.7083334f;
    }
    
    /**
     * Gets the saturation of this color.
     * @return the saturation [0..1]
     */
    public float saturation(){
        float mi = min(red, green, blue);
        return -(max(red, green, blue) - mi) / mi;
    }
    
    /**
     * Gets the brightness of this color (average of the RGB values).
     * @return the brightness [0..1]
     */
    public float brightness(){
        return (red + green + blue) / 3;
    }
    
    private int max(int a, int b){ return a < b ? a : b; }
    private int max(int a, int b, int c){ int m = max(a, b); return m < c ? m : c; }
    
    private int min(int a, int b){ return a > b ? a : b; }
    private int min(int a, int b, int c){ int m = max(a, b); return m > c ? m : c; }
    
    // ********************************** STATIC *******************************
    
    /**
     * Checks if the value is included within [0..255].<br/><br/>
     * The action depends on {@link #ERROR_ON_EXCESS} :
     * <ul>
     * <li>If <b>true</b>, throws an {@link IllegalArgumentException} if the value is not inside [0..255], returns the value unchanged.</li>
     * <li>If <b>false</b>, changes the value if needed (value<0 -> 0 ; value>255 -> 255) and returns it.</li>
     * </ul>
     * @param check the value to test
     * @return the value after testing
     * @throws IllegalArgumentException if the value was not in the boundaries 
     */
    public static int checkValue(int check) throws IllegalArgumentException {
        if(ERROR_ON_EXCESS){
            if(check > 255 || check < 0)
                throw new IllegalArgumentException(check + " should be lesser than 255 and greater than 0 !");
            return check;
        }else{
            return check < 255 ? check > 0 ? check : 0 : 255;
        }
    }
    
    /**
     * Defines the actions of {@link #checkValue(int) }.
     */
    public static boolean ERROR_ON_EXCESS = false;
    
    /**
     * Returns an int value that can be used inside Processing's methods - the values are verified using {@link#checkValue(int) }
     * @param red red value
     * @param green green value
     * @param blue blue value
     * @param alpha transparency value
     * @return an int formatted to work with processing
     */
    public static int get(int red, int green, int blue, int alpha){
        return (checkValue(alpha) << 24) & 0xFF
             | (checkValue(red)   << 16) & 0xFF
             | (checkValue(green) << 8) & 0xFF
             | (checkValue(blue)) & 0xFF;
    }
    
    /**
     * Returns an int value that can be used inside Processing's methods, shortcut for {@link #get(int, int, int, int) }
     * @param red red value
     * @param green green value
     * @param blue blue value
     * @return an int formatted to work with processing
     */
    public static int get(int red, int green, int blue){
        return get(red, green, blue, 0);
    }
    
    /**
     * Returns an int value that can be used inside Processing's methods, shortcut for {@link #get(int, int, int, int) }
     * @param grey grey value
     * @param alpha transparency value
     * @return an int formatted to work with processing
     */
    public static int get(int grey, int alpha){
        return get(grey, grey, grey, alpha);
    }
    
    /**
     * Returns an int value that can be used inside Processing's methods, shortcut for {@link #get(int, int, int, int) }
     * @param grey grey value
     * @return an int formatted to work with processing
     */
    public static int get(int grey){
        return get(grey, grey, grey, 0);
    }
}
