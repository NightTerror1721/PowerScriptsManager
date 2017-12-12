/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nt.ps.manager;

import java.util.HashMap;
import java.util.HashSet;
import java.util.function.Consumer;

/**
 *
 * @author Asus
 */
public class SourceScriptManager
{
    private final HashMap<String, SourceScript> scripts = new HashMap<>();
    private final HashSet<SourceSnippet> snippets = new HashSet<>();
    private final HashMap<String, Macro> macros = new HashMap<>();
    
    public final SourceScript createScript(String name)
    {
        if(scripts.containsKey(name))
            throw new IllegalArgumentException("Script " + name + " already exists");
        SourceScript s = new SourceScript(name);
        scripts.put(name, s);
        return s;
    }
    
    public final SourceScript getScript(String name) { return scripts.get(name); }
    
    public final boolean hasScript(String name) { return scripts.containsKey(name); }
    
    public final void removeScript(String name) { scripts.remove(name); }
    
    public final int getScriptCount() { return scripts.size(); }
    
    public final void forEachScript(Consumer<SourceScript> consumer) { scripts.values().forEach(consumer); }
    
    
    public final void insertSnippet(SourceSnippet snippet)
    {
        if(snippet == null)
            throw new NullPointerException();
        snippets.add(snippet);
    }
    
    public final boolean hasSnippet(SourceSnippet snippet) { return snippets.contains(snippet); }
    
    public final void removeSnippet(SourceSnippet snippet) { snippets.remove(snippet); }
    
    public final int getSnippetCount() { return snippets.size(); }
    
    public final void forEachSnippet(Consumer<SourceSnippet> consumer) { snippets.forEach(consumer); }
    
    
    public final void setMacro(Macro macro)
    {
        macros.put(macro.getName(), macro);
    }
    
    public final Macro getMacro(String name) { return macros.get(name); }
    
    public final boolean hasMacro(String name) { return macros.containsKey(name); }
    
    public final void removeMacro(String name) { macros.remove(name); }
    
    
    
}
