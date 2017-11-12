/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objects;

import java.util.ArrayList;
import java.util.HashMap;
import keys.Key;
import keys.KeyData;
import logger.Task;

/**
 * A window class
 * @author CLOVIS
 */
public class MWindow extends MContainer {
    
    private final HashMap<Key, KeyData> keys;
    private final ArrayList<Key> pressed;
    
    public MWindow(float pX, float pY, float sX, float sY) {
        super(pX, pY, sX, sY);
        keys = new HashMap<>();
        pressed = new ArrayList<>();
    }
    
    @Override
    public void onKeyPressed(Key k){
        if(getFocus() != null && getFocus().isWritable()){        //If it's writable, send it the key
            getFocus().onKeyPressed(k);
            
        }else if(keys.containsKey(k)){      //If it's not, notify its owner
            KeyData kdata = keys.get(k);
            kdata.press();
            if(kdata.isHoldMode()){         //If this key is classified as hold-enabled
                if(!pressed.contains(k))
                    pressed.add(k);
            }else{                          //Otherwise
                kdata.getOwner().onKeyPressed(k);
            }
        }
    }
    
    @Override
    public void onKeyReleased(Key k){
        if(getFocus() != null && getFocus().isWritable()){         //It it's writable, send it the key
            getFocus().onKeyReleased(k);
        }
        
        if(keys.containsKey(k)){             //Anyway, if the key is released notify its owner
            KeyData kdata = keys.get(k);
            kdata.release();
            if(kdata.isHoldMode()){
                while(pressed.contains(k))
                    pressed.remove(k);
            }else{
                kdata.getOwner().onKeyPressed(k);
            }
        }
    }
    
    public void notifyOwners(){
        for(int i = 0; i < pressed.size(); i++){
            Key k = pressed.get(i);
            keys.get(k).getOwner().onKeyHold(k);
        }
    }
    
    /**
     * Use this method to register data about a key.
     * @param k the targeted key
     * @param kd the data about the key
     */
    public void registerKey(Key k, KeyData kd){
        KeyData previous = keys.put(k, kd);
        
        Task.info("The key " + k.toString() + " has been registered by " + kd.getOwner().toString() + (previous == null ? "." : ", it was previously owned by " + previous.getOwner().toString()));
    }
    
    /**
     * Use this method to register data about multiple keys.
     * @param keys the targeted keys
     * @param kd the data about the keys
     */
    public void registerKeys(Key[] keys, KeyData kd){
        for(Key k : keys)
            registerKey(k, kd);
    }
}
