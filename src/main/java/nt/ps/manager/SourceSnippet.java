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
public final class SourceSnippet extends Snippet
{
    private String code = "";
    
    SourceSnippet() {}
    
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
    public final SourceSnippet source() { return this; }
}
