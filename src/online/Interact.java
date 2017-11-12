/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package online;

import util.MData;

/**
 * An interface for receiving String commands
 * @author CLOVIS
 */
public interface Interact {
    
    /**
     * Receives data from a connection.
     * @param command the command
     * @param parameters the parameters
     * @param ip IP of the sender
     */
    public void receive(String command, MData parameters, String ip);
    
}
