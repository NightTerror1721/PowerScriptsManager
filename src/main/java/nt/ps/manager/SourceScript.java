/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nt.ps.manager;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import nt.ps.lang.PSValue;
import nt.ps.lang.PSVarargs;

/**
 *
 * @author Asus
 */
public final class SourceScript extends Script
{
    private final HashMap<String, ScriptConstant> consts;
    private String[] pars;
    private String code;
    
    SourceScript(String name)
    {
        super(name);
        this.consts = new HashMap<>();
        this.pars = new String[] {};
        this.code = "";
    }
    
    public final void setConstant(String name, long value) { consts.put(name, new IntegerConstant(value)); }
    public final void setConstant(String name, double value) { consts.put(name, new FloatConstant(value)); }
    public final void setConstant(String name, boolean value) { consts.put(name, value ? C_TRUE : C_FALSE); }
    public final void setConstant(String name, String value) { consts.put(name, value == null ? C_NULL : new StringConstant(value)); }
    
    public final void removeConstant(String name) { consts.remove(name); }
    
    public final boolean hasConstant(String name) { return consts.containsKey(name); }
    
    public final int getConstantCount() { return consts.size(); }
    
    public final void forEachConstant(BiConsumer<String, ScriptConstant> consumer)
    {
        for(Map.Entry<String, ScriptConstant> e : consts.entrySet())
            consumer.accept(e.getKey(), e.getValue());
    }
    
    
    public final void setParameters(String... parameters)
    {
        for(String par : parameters)
        {
            if(par == null)
                throw new NullPointerException("Invalid null parameter");
            if(par.isEmpty())
                throw new IllegalArgumentException("Invalid empty parameter");
        }
        this.pars = parameters;
    }
    
    public final int getParameterCount() { return pars.length; }
    
    public final String getParameter(int index) { return pars[index]; }
    
    public final String[] getParameters() { return Arrays.copyOf(pars, pars.length); }
    
    
    public final void setCode(String code)
    {
        if(code == null)
            throw new NullPointerException();
        this.code = code;
    }
    
    public final String getCode() { return code; }
    
    
    @Override
    public final PSVarargs execute() { throw new UnsupportedOperationException(); }
    
    @Override
    public final PSVarargs execute(PSValue arg0) { throw new UnsupportedOperationException(); }
    
    @Override
    public final PSVarargs execute(PSValue arg0, PSValue arg1) { throw new UnsupportedOperationException(); }
    
    @Override
    public final PSVarargs execute(PSValue arg0, PSValue arg1, PSValue arg2) { throw new UnsupportedOperationException(); }
    
    @Override
    public final PSVarargs execute(PSValue arg0, PSValue arg1, PSValue arg2, PSValue arg3) { throw new UnsupportedOperationException(); }
    
    @Override
    public final PSVarargs execute(PSVarargs arg0) { throw new UnsupportedOperationException(); }

    @Override
    public final SourceScript source() { return this; }
    
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
        public double toFloat() { throw new UnsupportedOperationException(); }
        public boolean toBoolean() { throw new UnsupportedOperationException(); }
    }
    
    private static final class NullConstant extends ScriptConstant
    {
        private NullConstant() {}
        
        @Override
        public boolean isNull() { return true; }
        
        @Override
        public final String toString() { return "null"; }
    }
    private static final NullConstant C_NULL = new NullConstant();
    
    private static final class IntegerConstant extends ScriptConstant
    {
        private final long value;
        
        private IntegerConstant(long value) { this.value = value; }
        
        @Override
        public boolean isInteger() { return true; }
        
        @Override
        public long toInteger() { return value; }
        
        @Override
        public final String toString() { return Long.toString(value); }
    }
    
    private static final class FloatConstant extends ScriptConstant
    {
        private final double value;
        
        private FloatConstant(double value) { this.value = value; }
        
        @Override
        public boolean isFloat() { return true; }
        
        @Override
        public double toFloat() { return value; }
        
        @Override
        public final String toString() { return Double.toString(value); }
    }
    
    private static final class BooleanConstant extends ScriptConstant
    {
        private final boolean value;
        
        private BooleanConstant(boolean value) { this.value = value; }
        
        @Override
        public boolean isBoolean() { return true; }
        
        @Override
        public boolean toBoolean() { return value; }
        
        @Override
        public final String toString() { return value ? "true" : "false"; }
    }
    private static final BooleanConstant C_TRUE = new BooleanConstant(true);
    private static final BooleanConstant C_FALSE = new BooleanConstant(false);
    
    private static final class StringConstant extends ScriptConstant
    {
        private final String value;
        
        private StringConstant(String value) { this.value = value; }
        
        @Override
        public boolean isString() { return true; }
        
        @Override
        public final String toString() { return value; }
    }
}
