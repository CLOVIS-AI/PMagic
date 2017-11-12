/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objects;

import logger.Logger;
import logger.NullStream;
import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PGraphics;
import processing.core.PImage;
import styles.Color;

/**
 *
 * @author CLOVIS
 */
public class MPopUp extends MObject implements PConstants {
    
    private final String TITLE;
    private final String TEXT;
    private final PImage IMAGE;
    
    private int lifeTime;
    private Phase phase;
    
    private float alpha;
    
    private boolean isAlive;
    
    public MPopUp(int x, int y, String title, String text, PImage image){
        super(x, y, 300, 135);
        TITLE = title;
        TEXT = text;
        if(image != null){
            image.resize(100, 100);
            IMAGE = image;
        }else{
            IMAGE = null;
        }
        lifeTime = 0;
        phase = Phase.BEGIN;
        isAlive = true;
        logger.send("In "+x+":"+super.getPosX()+"\t"+y+":"+super.getPosY());
    }
    
    @Override
    public void draw(PGraphics g){
        if(!isAlive)    return;
        logger.begin("MPopUp.draw(PGraphics)");
        if(!isFocus())
            lifeTime++;
        logger.send("Lifetime : "+lifeTime+" ; Max time : "+maxTime);
        switch(phase){
            case BEGIN:
                alpha = PApplet.map(lifeTime, 0, tenthTime, 0, background.a());
                if(lifeTime >= tenthTime)
                    phase = Phase.BETWEEN;
                break;
            case BETWEEN:
                alpha = background.a();
                if(lifeTime >= maxTime - tenthTime)
                    phase = Phase.END;
                break;
            case END:
                alpha = PApplet.map(lifeTime, maxTime - tenthTime, maxTime, background.a(), 0);
                if(lifeTime >= maxTime)
                    remove();
                break;
        }
        if(isFocus())   alpha = 255;
        logger.send("Phase : "+phase+" ; Alpha : "+alpha);
        logger.send("Drawing ...");
        g.pushMatrix();
        g.translate(super.getPosX(), super.getPosY());
        g.noStroke();
        
        // BOX DRAWING
        logger.send("Box drawing ...");
        g.fill(background.r(), background.g(), background.b(), alpha);
        g.rect(0, 0, super.getSizeX(), super.getSizeY());
        
        // TITLE DRAWING
        logger.send("Title drawing ...");
        g.textAlign(LEFT, TOP);
        g.textSize(titleSize);
        g.fill(textColor.r(), textColor.g(), textColor.b(), alpha);
        g.text(TITLE, 5, 1);
        
        // SEPARATOR LINE
        logger.send("Separator line ...");
        g.stroke(100, alpha);
        g.line(5, titleSize + 5, super.getSizeX() - 5, titleSize + 5);
        
        //if(IMAGE != null){
            // IMAGE DRAWING
            logger.send("Image drawing ...");
            g.tint(alpha);
            //g.image(IMAGE, 5, titleSize + 10);
            g.rect(5, titleSize + 10, 100, 100);

            // TEXT DRAWING
            logger.send("Text drawing ...");
            g.textSize(textSize);
            g.text(TEXT, 110, titleSize + 10, 185, 90);
        /*}else{
            // TEXT DRAWING
            Logger.main.send("Text drawing ...");
            g.textSize(textSize);
            g.text(TEXT, 5, titleSize + 10, 290, 90);
        }*/
        
        g.popMatrix();
        logger.stop("Drawing finished ...");
    }
    
    private void remove(){
        isAlive = false;
        super.setSizeY(0);
    }
    
    
    
    @Override
    public boolean isAlive(){ return isAlive; }
    
    // ******************************* STATIC **********************************
    
    private static int maxTime;
    private static int halfTime;
    private static int tenthTime;
    
    private static Color background;
    private static Color textColor;
    private static int titleSize;
    private static int textSize;
    
    public static Logger logger;
    
    static{
        System.out.println("MPopUp > Resetting style settings ...");
        setTime(300);
        background = new Color(0, 200);
        textColor = new Color(255);
        titleSize = 20;
        textSize = 12;
        logger = new Logger(16, NullStream.NULL_PRINTSTREAM);
    }
    
    public static void setTime(int max) throws IllegalArgumentException {
        if(max < 20)
            throw new IllegalArgumentException("'max' should be greater than 20 : "+max);
        maxTime = max;
        halfTime = (int) (max / 2f);
        tenthTime = (int) (max / 10f);
    }
    
    public static void setBackground(Color bgd){
        background = bgd;
    }
    
    public static void setTextColor(Color txtColor){
        textColor = txtColor;
    }
    
    public static void setTextSizes(int title, int text){
        titleSize = title;
        textSize = text;
    }
    
    private static enum Phase{
        BEGIN, BETWEEN, END
    }
}
