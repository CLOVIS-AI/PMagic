/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package online;

import java.util.HashMap;
import util.MData;

/**
 * A class defining net communications with TCP
 * @author CLOVIS
 */
public abstract class TCP {
    
    private final HashMap<String, Interact> users;
    
    /**
     * Initializes a new TCP connection.
     */
    public TCP(){
        users = new HashMap<>();
    }
    
    /**
     * Updates the network.
     */
    public abstract void update();
    
    /**
     * Sends data to the network.
     * @param message the message you want to send
     */
    public abstract void send(String message);
    
    /**
     * Sends data to the network, in a formatted way.
     * @param command the command
     * @param parameters any parameters needed
     */
    public abstract void send(String command, MData parameters);
    
    /**
     * Stops the connection.
     */
    public abstract void stop();
    
    /**
     * Reserves a command for an Interact object. If this command is received, the owner will be notified.
     * @param command the command (must not contain 'spaces' characters)
     * @param owner the owner
     * @throws IllegalStateException if this command is already attributed 
     * @throws IllegalArgumentException if <code>command</code> contains spaces characters.
     */
    public final void registerCmd(String command, Interact owner) throws IllegalStateException, IllegalArgumentException {
        if(users.containsKey(command))
            throw new IllegalStateException("The command '" + command + "' is already attributed.");
        if(command.contains(" "))
            throw new IllegalArgumentException("The field 'command' should NOT contain spaces chars.");
        users.put(command, owner);
    }
    
    /**
     * Is this command reserved ?
     * @param command the command
     * @return <code>true</code> if it is.
     */
    public final boolean hasCommand(String command){
        return users.containsKey(command);
    }
    
    /**
     * Who reserved this command ?
     * @param command the command
     * @return Its owner
     */
    public final Interact getOwner(String command){
        return users.get(command);
    }
    
    /** Override this method to perform an action on disconnection. */
    public void onDisconnect(){}
    
    /** Override this method to perform an action when the received message is not formatted as a command. */
    public void onNotCommand(){}
    
    /**
     * Protects a message by replacing all dangerous characters by command-safe ones.
     * @param msg the message to protect
     * @return A protect String.
     */
    public static String protect(String msg){
        return msg.replace(' ', '_').replace(',', ';').replace('=', '-');
    }
    
    /**
     * Gets the original message back.
     * @param msg
     * @return 
     */
    public static String unprotect(String msg){
        return msg.replace('_', ' ').replace(';', ',').replace('-', '=');
    }
}
