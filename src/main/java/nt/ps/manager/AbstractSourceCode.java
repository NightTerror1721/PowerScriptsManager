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
abstract class AbstractSourceCode
{
    protected final SourceScriptManager base;

    public AbstractSourceCode(SourceScriptManager base)
    {
        if(base == null)
            throw new NullPointerException();
        this.base = base;
    }
    
    public final SourceScriptManager getScriptManagerParent() { return base; }
    
    
}
