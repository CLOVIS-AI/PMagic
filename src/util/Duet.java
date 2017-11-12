/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.util.Objects;

/**
 * A duet utility class, working with two elements : t and u, of respective types T and U.
 * @author CLOVIS
 */
public class Duet<T, U> {
    
    private T first;
    private U second;
    
    /**
     * Creates a Duet object with the two values set.
     * @param t first element
     * @param u second element
     */
    public Duet(T t, U u){
        first = t;
        second = u;
    }
    
    /**
     * Get the first element.
     * @return t
     */
    public T t(){
        return first;
    }
    
    /**
     * Set the first element.
     * @param n new value of t
     */
    public void t(T n){
        first = n;
    }
    
    /**
     * Get the second element.
     * @return u
     */
    public U u(){
        return second;
    }
    
    /**
     * Set the second element.
     * @param n new value of u
     */
    public void u(U n){
        second = n;
    }
    
    @Override
    public String toString(){
        return "{t=" + first.toString() + ";u=" + second.toString() + "}";
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 17 * hash + Objects.hashCode(this.first);
        hash = 17 * hash + Objects.hashCode(this.second);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Duet<?, ?> other = (Duet<?, ?>) obj;
        if (!Objects.equals(this.first, other.first)) {
            return false;
        }
        if (!Objects.equals(this.second, other.second)) {
            return false;
        }
        return true;
    }
}
