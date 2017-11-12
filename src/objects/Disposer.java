/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objects;

import java.util.HashMap;
import keys.Key;
import logger.Task;
import processing.core.PApplet;
import util.Duet;

/**
 * This class handles the animation between the different MWindows.
 * @author CLOVIS
 */
public class Disposer {
    
    private static HashMap<Duet<Integer, Integer>, MWindow> windows;
    private static float camX, camY;
    private static Direction direction;
    private static int movingTime, TIME;
    private static MWindow current;
    
    static{
        Task.begin("Initializing the Disposer ...");
        windows = new HashMap<>();
        camX = 0;
        camY = 0;
        direction = null;
        movingTime = 0;
        TIME = 500;
        current = null;
        Task.end("Disposer ready.");
    }
    
    public static void put(int x, int y, MWindow window){
        windows.put(new Duet(x, y), window);
    }
    
    public static void put(Duet<Integer, Integer> coords, MWindow window){
        windows.put(coords, window);
    }
    
    /**
     * Gets the MWindow object assigned to some coordinates.
     * @param coords Where the window is situated.
     * @return The MWindow object if there is one, otherwise returns <code>null</code>.
     */
    public static MWindow get(Duet<Integer, Integer> coords){
        try{
            return windows.get(coords);
        }catch(NullPointerException e){
            return null;
        }
    }
    
    public static MWindow remove(Duet<Integer, Integer> coords){
        return windows.remove(coords);
    }
    
    public static void teleport(int x, int y){
        Task.info("Disposer", "Teleporting towards : {x=" + x + ",y=" + y + "}");
        camX = x; camY = y;
    }
    
    public static void moveTo(Direction dir){
        direction = dir;
        movingTime = 0;
        Task.info("Disposer", "Moving towards : " + dir);
    }
    
    public static void draw(PApplet p){
        updatePosition(p);
    }
    
    public enum Direction{
        TOP, BOTTOM, LEFT, RIGHT
    }
    
    private static void updatePosition(PApplet g){
        movingTime++;
        if(movingTime >= TIME){
            Task.info("Disposer", "Finished sliding.");
            direction = null;
            camX = (int)camX;
            camY = (int)camY;
            movingTime = 0;
        }
        if(direction != null)
            switch(direction){
                case TOP:    camY -= 5; break;
                case BOTTOM: camY += 5; break;
                case LEFT:   camX -= 5; break;
                case RIGHT:  camX += 5; break;
                default:     break;
            }
        g.translate(-camX, -camY);
        if(direction == null){
            Duet<Integer, Integer> coords = new Duet((int)camX, (int)camY);
            current = windows.get(coords);
            // System.out.println(coords);
            drawAt(g, windows.get(coords), coords);
        }else{
            current = null;
            // Drawing the top-left corner
            Duet<Integer, Integer> coords = new Duet((int)camX, (int)camY);
            drawAt(g, windows.get(coords), coords);
            // Drawing the top-right corner
            coords = new Duet((int)camX+1, (int)camY);
            drawAt(g, windows.get(coords), coords);
            // Drawing the bottom-left corner
            coords = new Duet((int)camX, (int)camY+1);
            drawAt(g, windows.get(coords), coords);
        }
    }
    
    private static void drawAt(PApplet g, MObject obj, Duet<Integer, Integer> coords){
        if(obj == null) return;
        g.pushMatrix();
        g.translate(coords.t() * g.width, coords.t() * g.height);
        obj.collides(g.mouseX, g.mouseY);
        obj.draw(g.g);
        g.popMatrix();
    }
    
    public static void setAnimation(int slideTime){
        TIME = slideTime;
    }
    
    public static boolean isSliding(){
        return direction == null;
    }
    
    /**
     * Tells the current window that the mouse has been pressed. 
     * Does nothing during sliding.
     */
    public static void onMousePressed(){
        if(current != null){
            current.onMousePressed();
        }
    }
    
    /**
     * Tells the current window that the mouse has been released. 
     * Does nothing during sliding.
     */
    public static void onMouseReleased(){
        if(current != null){
            current.onMouseReleased();
        }
    }
    
    /**
     * Tells the current window that the key has been pressed. 
     * Does nothing during sliding.
     * @param k the key
     */
    public static void onKeyPressed(Key k){
        if(current != null){
            Task.info("Disposer", "Pressed key " + k.toString());
            current.onKeyPressed(k);
        }
    }
    
    /**
     * Tells the current window that the key has been released. 
     * Does nothing during sliding.
     * @param k the key
     */
    public static void onKeyReleased(Key k){
        if(current != null){
            current.onKeyReleased(k);
        }
    }
}
