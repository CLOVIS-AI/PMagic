/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logger;

import java.io.OutputStream;
import java.io.PrintStream;

/**
 * Use this as a normal {@link PrintStream} except that it doesn't write anything.
 * @author CLOVIS
 */
public class NullStream extends OutputStream {

    /**
     * Creates a NullStream obbject (effectively does nothing).
     */
    public NullStream(){
        
    }
    
    /**
     * Does nothing.
     * @param b the byte you usually use
     */
    @Override
    public void write(int b){}
    
    // *************************** STATIC **************************************
    
    /** An empty {@link PrintStream}. */
    public static final PrintStream NULL_PRINTSTREAM;
    
    /** An empty {@link OutputStream}. */
    public static final OutputStream NULL_OUTPUTSTREAM;
    
    static{
        NULL_OUTPUTSTREAM = new NullStream();
        NULL_PRINTSTREAM = new PrintStream(NULL_OUTPUTSTREAM);
    }
    
}
