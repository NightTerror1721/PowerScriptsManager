/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nt.ps.manager;

import java.util.LinkedList;
import java.util.function.Supplier;
import nt.ps.manager.Macro.ConstantMacroPart;
import nt.ps.manager.Macro.MacroPart;
import nt.ps.manager.Macro.VariableMacroPart;

/**
 *
 * @author Asus
 */
public final class MacroBuilder
{
    private String name;
    private final LinkedList<MacroPart> parts;
    private final StringBuilder sb;
    
    private MacroBuilder(String name)
    {
        parts = new LinkedList<>();
        sb = new StringBuilder();
        setName(name);
    }
    
    public static final MacroBuilder create(String name) { return new MacroBuilder(name); }
    
    public final Macro build()
    {
        if(sb.length() > 0)
        {
            parts.add(new ConstantMacroPart(false, sb.toString()));
            sb.delete(0, sb.length());
        }
        return new Macro(name, parts.toArray(new MacroPart[parts.size()]));
    }
    
    public final MacroBuilder setName(String name)
    {
        if(name == null)
            throw new NullPointerException();
        if(name.isEmpty())
            throw new IllegalArgumentException("Invalid empty macro name");
        this.name = name;
        return this;
    }
    
    public final MacroBuilder macro(String value)
    {
        if(value == null)
            throw new NullPointerException();
        if(sb.length() > 0)
        {
            parts.add(new ConstantMacroPart(false, sb.toString()));
            sb.delete(0, sb.length());
        }
        parts.add(new ConstantMacroPart(true, value));
        return this;
    }
    
    public final MacroBuilder put(Object value) { sb.append(value); return this; }
    public final MacroBuilder put(String value) { sb.append(value); return this; }
    public final MacroBuilder put(byte value) { sb.append(value); return this; }
    public final MacroBuilder put(short value) { sb.append(value); return this; }
    public final MacroBuilder put(int value) { sb.append(value); return this; }
    public final MacroBuilder put(long value) { sb.append(value); return this; }
    public final MacroBuilder put(float value) { sb.append(value); return this; }
    public final MacroBuilder put(double value) { sb.append(value); return this; }
    public final MacroBuilder put(boolean value) { sb.append(value); return this; }
    public final MacroBuilder put(char value) { sb.append(value); return this; }
    public final MacroBuilder put(CharSequence value) { sb.append(value); return this; }
    
    
    public final MacroBuilder macro(Supplier<String> value)
    {
        if(value == null)
            throw new NullPointerException();
        if(sb.length() > 0)
        {
            parts.add(new ConstantMacroPart(false, sb.toString()));
            sb.delete(0, sb.length());
        }
        parts.add(new VariableMacroPart(true, () -> value.get()));
        return this;
    }
    
    public final MacroBuilder var(Supplier<String> value)
    {
        if(value == null)
            throw new NullPointerException();
        if(sb.length() > 0)
        {
            parts.add(new ConstantMacroPart(false, sb.toString()));
            sb.delete(0, sb.length());
        }
        parts.add(new VariableMacroPart(false, value));
        return this;
    }
    
}
