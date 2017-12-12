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
public abstract class Script extends PSCode<SourceScript>
{
    private final String name;
    
    Script(String name)
    {
        if(name == null)
            throw new NullPointerException();
        if(name.isEmpty())
            throw new IllegalArgumentException("Invalid empty name");
        this.name = name;
    }
    
    public final String getName() { return name; }
}
