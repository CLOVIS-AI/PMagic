/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objects;

import keys.Key;
import logger.Task;
import processing.core.PApplet;
import processing.core.PGraphics;

/**
 *
 * @author CLOVIS
 */
public class WindowHandler {
    
    private WindowHandler(){}
    
    private static MWindow current;
    private static boolean showDebugData = false;
    
    public static void setWindow(MWindow window){
        if(current != null)
            current.endFocus();
        
        current = window;
        
        current.setPosX(0);
        current.setPosY(0);
    }
    
    /** Enable the data display (disabled by default). */
    public static void enableDebugDataOverlay(){ showDebugData = true; }
    
    /** Disable the data display. */
    public static void disableDebugDataOverlay(){ showDebugData = false; }
    
    public static void draw(PApplet g){
        try{
            g.pushMatrix();
            current.draw(g.g);
        }catch(NullPointerException e){
        }finally{
            g.popMatrix();
        }
        
        if(showDebugData)
            drawData(g);
    }
    
    private static void drawData(PApplet g){
        g.fill(0);
        g.rect(10, 10, 200, 150);
        g.fill(255);
        g.textSize(10);
        int totalMemory = (int)(Runtime.getRuntime().totalMemory() * 0.000_001);
        int maximumMemory = (int)(Runtime.getRuntime().maxMemory() * 0.000_001);
        float memoryUsage = (int)((float)totalMemory/maximumMemory * 100);
        String text = "DEBUG DATA\nFPS : " + (int)g.frameRate + "\nMemory : " + totalMemory + "/" + maximumMemory + " MB (" + memoryUsage + " %)"+"\nMouse :\n    X = " + g.mouseX + "/" + g.width + "\n    Y = " + g.mouseY + "/" + g.height;
        g.text(text, 15, 40);
    }
    
    public static void onMousePressed(){
        try{
            current.onMousePressed();
        }catch(NullPointerException e){}
    }
    
    public static void onMouseReleased(){
        try{
            current.onMouseReleased();
        }catch(NullPointerException e){}
    }
    
    public static void onKeyPressed(Key k){
        try{
            current.onKeyPressed(k);
        }catch(NullPointerException e){}
    }
    
    public static void onKeyReleased(Key k){
        try{
            current.onKeyReleased(k);
        }catch(NullPointerException e){}
    }
    
    public static void notifyKeyOwners(){
        try{
            current.notifyOwners();
        }catch(NullPointerException e){}
    }
    
    public static void mouseCoordinates(float x, float y){
        try{
            current.collides(x, y);
        }catch(NullPointerException e){}
    }
}
