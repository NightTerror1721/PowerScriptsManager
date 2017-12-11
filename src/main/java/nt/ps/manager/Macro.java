/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nt.ps.manager;

import java.util.Iterator;
import java.util.Objects;
import java.util.function.Supplier;

/**
 *
 * @author Asus
 */
public final class Macro implements Comparable<Macro>
{
    private final String name;
    private final MacroPart[] parts;
    
    Macro(String name, MacroPart[] parts)
    {
        if(name == null)
            throw new NullPointerException();
        if(name.isEmpty())
            throw new IllegalArgumentException("Invalid empty macro name");
        this.name = name;
        this.parts = parts;
    }
    
    public final String getName() { return name; }
    
    final Iterable<MacroPart> parts()
    {
        return () -> new Iterator<MacroPart>()
        {
            private int it = 0;
            
            @Override
            public final boolean hasNext() { return it < parts.length; }

            @Override
            public final MacroPart next() { return parts[it++]; }
        };
    }
    
    @Override
    public final boolean equals(Object o)
    {
        return o != null &&  o instanceof Macro &&
                name.equals(((Macro)o).name);
    }

    @Override
    public final int hashCode()
    {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.name);
        return hash;
    }

    @Override
    public final int compareTo(Macro o) { return name.compareTo(o.name); }
    
    static abstract class MacroPart
    {
        private final boolean ref;
        
        private MacroPart(boolean isRef)
        {
            this.ref = isRef;
        }
        
        boolean isString() { return !ref; }
        boolean isMacroRef() { return ref; }
        
        public abstract String getString();
    }
    
    static final class ConstantMacroPart extends MacroPart
    {
        private final String string;
        
        ConstantMacroPart(boolean isRef, String string)
        {
            super(isRef);
            this.string = string;
        }
        
        @Override
        public final String getString() { return string; }
    }
    
    static final class VariableMacroPart extends MacroPart
    {
        private final Supplier<String> var;
        
        VariableMacroPart(boolean isRef, Supplier<String> var)
        {
            super(isRef);
            this.var = var;
        }
        
        @Override
        public final String getString() { return var.get(); }
    }
}
