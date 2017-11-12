/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.util.Objects;

/**
 * A tripplet utility class, working with 3 elements : t, u and v of respective types T, U and V.
 * @author CLOVIS
 */
public class Triplet<T, U, V> {
    
    private T first;
    private U second;
    private V third;
    
    /**
     * Creates a Triplet object with the three values set.
     * @param t first element
     * @param u second element
     * @param v third element
     */
    public Triplet(T t, U u, V v){
        first = t;
        second = u;
        third = v;
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
    
    /**
     * Get the third element.
     * @return v
     */
    public V v(){
        return third;
    }
    
    /**
     * Set the third element.
     * @param n new value of v
     */
    public void v(V n){
        third = n;
    }
    
    @Override
    public String toString(){
        return first + ";" + second + ";" + third;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + Objects.hashCode(this.first);
        hash = 79 * hash + Objects.hashCode(this.second);
        hash = 79 * hash + Objects.hashCode(this.third);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj)     return true;
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Triplet<T, U, V> other = (Triplet<T, U, V>) obj;
        if (!Objects.equals(this.first, other.first)) {
            return false;
        }
        if (!Objects.equals(this.second, other.second)) {
            return false;
        }
        if (!Objects.equals(this.third, other.third)) {
            return false;
        }
        return true;
    }
}
