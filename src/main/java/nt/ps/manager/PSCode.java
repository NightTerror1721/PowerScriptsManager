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
 * @param <SC>
 */
public abstract class PSCode<SC extends PSCode>
{
    PSCode() {}
    
    public abstract PSVarargs execute();
    public abstract PSVarargs execute(PSValue arg0);
    public abstract PSVarargs execute(PSValue arg0, PSValue arg1);
    public abstract PSVarargs execute(PSValue arg0, PSValue arg1, PSValue arg2);
    public abstract PSVarargs execute(PSValue arg0, PSValue arg1, PSValue arg2, PSValue arg3);
    public abstract PSVarargs execute(PSVarargs args);
    
    public final PSVarargs execute(PSValue... args) { return execute(PSVarargs.varargsOf(args)); }
    
    public abstract SC source();
}
