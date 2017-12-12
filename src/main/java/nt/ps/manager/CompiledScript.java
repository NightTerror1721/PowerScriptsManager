/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nt.ps.manager;

import nt.ps.lang.PSValue;
import nt.ps.lang.PSVarargs;

/**
 *
 * @author Asus
 */
public final class CompiledScript extends Script
{
    private final PSValue script;
    
    CompiledScript(String name, PSValue script)
    {
        super(name);
        this.script = script;
    }
    
    public final PSValue getConstant(String name) { return script.getProperty(name); }
    public final boolean hasConstant(String name) { return script.getProperty(name) != PSValue.UNDEFINED; }
    
    @Override
    public PSVarargs execute() { return script.call(); }

    @Override
    public PSVarargs execute(PSValue arg0) { return script.call(arg0); }

    @Override
    public PSVarargs execute(PSValue arg0, PSValue arg1) { return script.call(arg0, arg1); }

    @Override
    public PSVarargs execute(PSValue arg0, PSValue arg1, PSValue arg2) { return script.call(arg0, arg1, arg2); }

    @Override
    public PSVarargs execute(PSValue arg0, PSValue arg1, PSValue arg2, PSValue arg3) { return script.call(arg0, arg1, arg2, arg3); }

    @Override
    public PSVarargs execute(PSVarargs args) { return script.call(args); }

    @Override
    public SourceScript source() { throw new UnsupportedOperationException(); }
    
}
