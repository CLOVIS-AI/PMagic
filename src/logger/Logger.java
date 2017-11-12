/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logger;

import java.io.PrintStream;

/**
 * A simple logger class
 * @author CLOVIS
 */
public class Logger {
    
    private long[] layers;
    private int currentLayer;
    private PrintStream output;
    
    /**
     * Creates a new logger object. If you only need one of them, consider using {@link #main} instead.
     * @param depth max. number of tasks (if the depth is too little, it will be increased automatically. 2 if lesser than 2)
     * @param outputStream the output of this logger (put {@link System#out} for standard log output)
     */
    public Logger(int depth, PrintStream outputStream){
        if(depth < 2){
            System.err.println("Minimum depth is 2");
        }
        layers = new long[depth];
        currentLayer = 0;
        output = outputStream;
    }
    
    /**
     * Begins a new task.
     * @param source name of the task
     * @param message message
     * @see #begin(java.lang.String)
     */
    public void begin(String source, String message){
        output.println(spaces[currentLayer] + "- " + source + " :\t" + message);
        begin();
    }
    
    /**
     * Begins a new task.
     * @param source name of the task
     * @see #begin(java.lang.String, java.lang.String)
     */
    public void begin(String source){
        output.println(spaces[currentLayer] + "- " + source);
        begin();
    }
    
    /**
     * The logic behind beginning a new task.
     */
    private void begin(){
        currentLayer++;
        if(currentLayer >= layers.length-1){
            System.err.println("Logger depth ("+layers.length+") is not enough, resizing logging array.");
            long[] newLayers = new long[layers.length + 1];
            for(int i = 0; i < newLayers.length; i++)
                newLayers[i] = layers[i];
            newLayers[newLayers.length+1] = System.currentTimeMillis();
            layers = newLayers;
        }
        layers[currentLayer] = System.currentTimeMillis();
    }
    
    /**
     * Sends information about the current task.
     * @param message informations
     */
    public void send(String message){
        long currentTime = System.currentTimeMillis();
        output.println(spaces[currentLayer] + (currentTime-layers[currentLayer]) + "\t" + message);
        layers[currentLayer] = currentTime;
    }
    
    /**
     * Stops the current task, depiles it.
     */
    public void stop(){
        currentLayer--;
    }
    
    /**
     * Sends a message then stops the current task and depiles it.
     * @param message informations
     */
    public void stop(String message){
        send(message);
        stop();
    }
    
    // ******************************* STATIC **********************************
    
    private static final String[] spaces;
    /** A Logger object, with default depth of 16 and output set to {@link System#out}, used for convenience. */
    public static Logger main;
    
    static{
        main = new Logger(16, System.out);
        spaces = new String[200];
        spaces[0] = "";
        for(int i = 1; i < 200; i++){
            spaces[i] = spaces[i-1] + "  ";
        }
    }
}
