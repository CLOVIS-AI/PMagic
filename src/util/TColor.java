/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.util.Objects;

/**
 * A shortcut to use colors with Triplet.
 * @author CLOVIS
 */
public class TColor extends Triplet<Integer, Integer, Integer> {

    private float alpha;
    
    /**
     * Creates a TColor object.
     * @param r red value
     * @param g green value
     * @param b blue value
     * @param a alpha value
     */
    public TColor(int r, int g, int b, float a) {
        super(r, g, b);
        alpha = a;
    }
    
    public float a(){
        return alpha;
    }
    
    public void a(float a){
        alpha = a;
    }
    
    @Override
    public String toString(){
        return t() + ";" + u() + ";" + v() + ";" + alpha;
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 73 * hash + Objects.hashCode(super.t());
        hash = 73 * hash + Objects.hashCode(super.u());
        hash = 73 * hash + Objects.hashCode(super.v());
        hash = 73 * hash + Objects.hashCode(alpha);
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
        final TColor other = (TColor) obj;
        if (!Objects.equals(super.t(), other.t())) {
            return false;
        }
        if (!Objects.equals(super.u(), other.u())) {
            return false;
        }
        if (!Objects.equals(super.v(), other.v())) {
            return false;
        }
        if (!Objects.equals(alpha, other.a())) {
            return false;
        }
        return true;
    }
}
