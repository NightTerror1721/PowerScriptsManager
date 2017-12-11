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
public final class SourceSnippet extends Snippet
{
    private final SourceScriptManager base;
    private String code = "";
    
    SourceSnippet(SourceScriptManager base)
    {
        if(base == null)
            throw new NullPointerException();
        this.base = base;
    }
    
    public final void setCode(String code)
    {
        if(code == null)
            throw new NullPointerException();
        this.code = code;
    }
    
    public final String getCode() { return code; }

    @Override
    public final void execute() { throw new UnsupportedOperationException(); }

    @Override
    public final SourceSnippet source() { return this; }
}
