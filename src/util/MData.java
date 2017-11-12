/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.util.Arrays;
import java.util.HashMap;
import logger.Task;

/**
 * An object holding different types of data.
 * @author CLOVIS
 */
public class MData {
    
    private final HashMap<String, MData> values;
    
    /**
     * Creates an empty MData array.
     */
    public MData(){
        values = new HashMap<>();
    }
    
    public static MData newMData(String data){
        MData ret;
        
        Task.begin("Parsing " + data);
        
        if(data.charAt(0) == '{'){
            Task.info("Recognized as MData array.");
            
            Task.info("Initializing MData array ...");
            ret = new MData();
            
            Task.info("Deleting beginning and ending {}");
            data = data.substring(1);
            data = data.substring(0, data.length() - 1);

            Task.info("Spliting data : " + data);
            String parts[] = data.split(",");

            Task.begin("Going through " + Arrays.toString(parts));
            for(String part : parts){
                Task.info("Couple : " + part);
                String[] v = part.split("=");
                
                if(v.length > 2){
                    Task.info("Associating back parameters");
                    for(int i = 2; i < v.length; i++)
                        v[1] += "=" + v[i];
                    Task.info("Finished ; arguments are : " + v[1]);
                }
                
                ret.put(v[0], newMData(v[1]));
            }
            Task.end("Finished.");
        }else{
            Task.info("Recognized as Primitive value.");
            
            ret = new Primitive(online.TCP.protect(data));
        }
        
        Task.end("Finished parsing.");
        return ret;
    }
    
    /**
     * Puts a MData value in this MData array.
     * @param name key to access this value
     * @param value the value itself
     */
    public void put(String name, MData value){
        values.put(name, value);
    }
    
    /**
     * Gets the value associated with the name.
     * @param name the name of the value you'd like
     * @return The value associated with <code>name</code>, or <code>null</code> if there is none.
     */
    public MData get(String name){
        return values.get(name);
    }
    
    @Override
    public String toString(){
        String ret = "{";
        for(String key : values.keySet()){
            ret += key + "=" + values.get(key).toString() + ",";
        }
        ret = ret.substring(0, ret.length() - 1);
        ret += "}";
        return ret;
    }
}
