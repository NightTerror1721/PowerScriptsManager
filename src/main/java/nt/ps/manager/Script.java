/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nt.ps.manager;

/**
 *
 * @author Asus
 */
public final class Script
{
    private final String name;
    private String code;
    
    public static abstract class ScriptConstant
    {
        @Override
        public abstract String toString();
        
        public boolean isNull() { return false; }
        public boolean isInteger() { return false; }
        public boolean isFloat() { return false; }
        public boolean isBoolean() { return false; }
        public boolean isString() { return false; }
        
        public long toInteger() { throw new UnsupportedOperationException(); }
        public long toFloat() { throw new UnsupportedOperationException(); }
        public long toBoolean() { throw new UnsupportedOperationException(); }
    }
    
    
}
