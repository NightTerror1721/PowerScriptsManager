/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nt.ps.manager;

import java.util.HashMap;
import java.util.HashSet;

/**
 *
 * @author Asus
 */
public class SourceScriptManager
{
    private final HashMap<String, Script> scripts = new HashMap<>();
    private final HashSet<SourceSnippet> snippets = new HashSet<>();
    private final HashMap<String, Macro> macros = new HashMap<>();
    
}
