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
public final class CompiledSnippet extends Snippet
{
    private final PSValue code;
    private final PSValue id;
    
    CompiledSnippet(PSValue code, PSValue id)
    {
        this.code = code;
        this.id = id;
    }

    @Override
    public final PSVarargs execute() { return code.call(id); }

    @Override
    public final PSVarargs execute(PSValue arg0) { return code.call(id, arg0); }

    @Override
    public final PSVarargs execute(PSValue arg0, PSValue arg1) { return code.call(id, arg0, arg1); }

    @Override
    public final PSVarargs execute(PSValue arg0, PSValue arg1, PSValue arg2) { return code.call(id, arg0, arg1, arg2); }

    @Override
    public final PSVarargs execute(PSValue arg0, PSValue arg1, PSValue arg2, PSValue arg3) { return code.call(PSVarargs.varargsOf(id, arg0, arg1, arg2, arg3)); }

    @Override
    public final PSVarargs execute(PSVarargs args) { return code.call(PSVarargs.varargsOf(id, args)); }

    @Override
    public final SourceSnippet source() { throw new UnsupportedOperationException(); }
}
