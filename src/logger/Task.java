/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logger;

import java.util.ArrayList;

/**
 * Send task informations as a log, inspired from StatsReader's tasks methods.<br/><br/>
 * 
 * This class works with a stack of tasks ; {@link #begin() begin()} and 
 * {@link #begin(java.lang.String) begin(String)} add a new task, 
 * {@link #end(java.lang.String) end(String)} removes the last task from the
 * stack, and {@link #getTime() getTime()} and 
 * {@link #info(java.lang.String) info(String)} are used to interact with the
 * current task (there are no ways to interact with other tasks than the one on
 * top of the stack).
 * 
 * @author CLOVIS
 */
public class Task {
    
    private static final ArrayList<Long> layers;
    private static final String[] spaces;
    
    private Task(){}
    
    /**
     * Creates a new task.
     * @see #begin(java.lang.String) Create a task and send an information
     */
    public static void begin(){
        layers.add(System.currentTimeMillis());
    }
    
    /**
     * Creates a new task and sends an information.<br/>
     * Effectively, this method is the same as :
     * <pre>
     * Task.info("›" + info);
     * Task.begin();
     * </pre>
     * <b>Note.<b/> You should not use this method for the root task ! Use {@link #begin() } instead.
     * @param info Information about the task
     * @see #begin() To create a task without sending information
     */
    public static void begin(String info){
        info("›" + info);
        begin();
    }
    
    /**
     * Sends information about the current task.
     * @param info Information about the task
     * @return How muched time has passed since the last reset of the timer (effectively since the last call of either {@link #info(java.lang.String) info(String)}, {@link #getTime() getTime()} or {@link #end(java.lang.String) end(String)}), in milliseconds (according to {@link System#currentTimeMillis() }).
     */
    public static long info(String info){
        long time = getTime();
        String t = String.valueOf(time);
        System.out.println(spaces[layers.size()-1] + 
                           t + 
                           spaces[8 - t.length()] + 
                           info);
        return time;
    }
    
    /**
     * Sends information about the current task.
     * @param source Source of the information
     * @param info Information about the task
     * @return How muched time has passed since the last reset of the timer (effectively since the last call of either {@link #info(java.lang.String) info(String)}, {@link #getTime() getTime()} or {@link #end(java.lang.String) end(String)}), in milliseconds (according to {@link System#currentTimeMillis() }).
     */
    public static long info(String source, String info){
        long time = getTime();
        String t = String.valueOf(time);
        System.out.println(spaces[layers.size()-1] + 
                           t + 
                           spaces[8 - t.length()] + 
                           source + " › " + info);
        return time;
    }
    
    /**
     * Prints the 'header' that {@link #info(java.lang.String) info()} would print, without text of EOL char, so you can print your own custom message.
     */
    public static void printTime(){
        long time = getTime();
        String t = String.valueOf(time);
        System.out.print(spaces[layers.size()-1] + 
                         t + 
                         spaces[8 - t.length()]);
    }
    
    /**
     * Sends information about the current task without refreshing the timer,
     * to use when the time that has passed is irrelevant.
     * @param info Information about the task
     */
    public static void infoUntimed(String info){
        System.out.println(spaces[layers.size()-1] + 
                           spaces[8] + 
                           info);
    }
    
    /**
     * Sends information about the current task with or without refreshing the timer.<br/><br/>
     * @param info Information about the task
     * @param isTimeRelevant <code>true</code> if you care about the time, <code>false</code> if you don't.
     * @return <b>if <code>isTimeRelevant</code> : </b> <pre>return info(info);</pre>
     *         <b>else : </b><pre>return 0;</pre>
     */
    public static long info(String info, boolean isTimeRelevant){
        if(isTimeRelevant){ return info(info);            }
        else{               infoUntimed(info); return 0l; }
    }
    
    /**
     * Gets the time that passed since the last call.
     * @return How muched time has passed since the last reset of the timer (effectively since the last call of either {@link #info(java.lang.String) info(String)}, {@link #getTime() getTime()} or {@link #end(java.lang.String) end(String)}), in milliseconds (according to {@link System#currentTimeMillis() }). 
     */
    public static long getTime(){
        long time = (System.currentTimeMillis() - layers.get(layers.size() - 1));
        layers.set(layers.size()-1, System.currentTimeMillis());
        return time;
    }
    
    /**
     * Ends the last created task.
     * @param info Information about the end of the task
     * @return How much time has passed since the beginning of the task.
     */
    public static long end(String info){
        if(layers.size() > 0)
            layers.remove(layers.size()-1);
        return info("‹"+info);
    }
    
    static{
        layers = new ArrayList<>(10);
        spaces = new String[64];
        String s = "";
        for(int i = 0; i < 64; i++)
            spaces[i] = (s+=" ");
    }
}
