/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package online;

import java.util.ArrayList;
import java.util.Arrays;
import logger.Task;
import processing.core.PApplet;
import processing.net.Client;
import util.MData;

/**
 * A TCP Client
 * @author CLOVIS
 */
public class TCPClient extends TCP {

    private final Client client;
    
    private final ArrayList<Byte> received;
    
    /**
     * Connects to the server using TCP
     * @param source requested by Processing
     * @param server the IP of the server you want to reach
     * @param port the server's open port
     */
    public TCPClient(PApplet source, String server, int port){
        Task.begin("Connecting to " + server + " on port " + port);
        client = new Client(source, server, port);
        Task.end("Connected.");
        Task.info("Initializing variables ...");
        received = new ArrayList<>();
        Task.info("Done.");
    }
    
    @Override
    @SuppressWarnings("UnusedAssignment")
    public void update() {
        if(!client.active()){ System.err.println("Disconnected from server !"); super.onDisconnect(); }
        while(client.available() > 0){
            byte next = (byte) client.read();
            if(next == (byte) ('\n')){
                Task.begin("TCPClient : Received a message ...");
                
                Task.info("Analyzing received text ...");
                byte[] bytes = new byte[received.size()];
                for(int i = 0; i < received.size(); i++)
                    bytes[i] = received.get(i);
                received.clear();
                String line = new String(bytes);
                Task.info("Received : [" + line + "]");
                
                Task.info("Is it a command ?");
                if(line.charAt(0) == '>'){
                    Task.info("Yes.");
                    line = line.replaceFirst(">", "");
                    String[] parts = line.split(" ");
                    
                    Task.info("Received", Arrays.toString(parts));
                    
                    String cmd = parts[0];
                    Task.info("Command : " + cmd);
                    if(hasCommand(cmd)){
                        Task.info("This command has been reserved.");
                        
                        Task.info("Requesting owner ...");
                        Interact owner = getOwner(cmd);
                        
                        Task.info("Notifying owner ...");
                        owner.receive(cmd, MData.newMData(parts[1]), null);
                    }else{
                        System.err.println("The command " + parts[0] + " has been received, but is not reserved !");
                    }
                }else{
                    Task.info("No.");
                    super.onNotCommand();
                }
                
                Task.end("Command treated.");
            }else{
                received.add(next);
            }
        }
    }

    @Override
    public void send(String message) {
        client.write(message + "\n");
    }
    
    @Override
    public void send(String command, MData parameters){
        send(">" + command + " " + parameters.toString());
    }

    @Override
    public void stop() {
        client.stop();
    }
    
}
