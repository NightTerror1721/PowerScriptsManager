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
public abstract class Snippet
{
    Snippet() {}
    
    public abstract void execute();
    public abstract SourceSnippet source();
}
