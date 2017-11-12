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
import processing.net.Server;
import util.MData;

/**
 * A TCP Server.
 * @author CLOVIS
 */
public class TCPServer extends TCP {
    
    private final Server server;
    private ArrayList<Client> clients;
    
    /**
     * Creates a new server using TCP.
     * @param source needed by Processing
     * @param port the port on which to listen for requests
     */
    public TCPServer(PApplet source, int port){
        Task.begin("Creating server on port " + port);
        server = new Server(source, port);
        Task.end("Listenning.");
        clients = new ArrayList<>();
        Task.info("Done.");
    }
    
    @Override
    public void update() {
        if(!server.active()){ System.err.println("The server has crashed !"); onDisconnect(); return; }
        Client sender;
        while((sender = server.available()) != null){
            if(!clients.contains(sender))
                clients.add(sender);
            while(sender.available() > 0){
                Task.begin("TCPServer : Received a command ...");

                String line = sender.readStringUntil('\n');
                line = line.replace("\n", "");
                Task.info("Command", line);
                
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
                        owner.receive(cmd, MData.newMData(parts[1]), sender.ip());
                    }else{
                        System.err.println("The command '" + parts[0] + "' has been received, but is not reserved !");
                    }
                }else{
                    Task.info("No.");
                    super.onNotCommand();
                }
                Task.end("Command treated.");
            }
        }
    }

    @Override
    public void send(String message) {
        Task.info("SENT : [" + message + "]");
        server.write(message + "\n");
    }

    @Override
    public void send(String command, MData parameters) {
        send(">" + command + " " + parameters.toString());
    }
    
    public void sendTo(String command, MData parameters, String ip){
        for(Client c : clients)
            if(c.ip() == null ? ip == null : c.ip().equals(ip)){
                Task.info("SENT TO " + ip + " : [" + ">" + command + " " + parameters.toString() + "]");
                c.write(">" + command + " " + parameters.toString() + "\n");
            }
    }

    @Override
    public void stop() {
        server.stop();
    }
    
}
