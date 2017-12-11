/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nt.ps.manager;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import nt.ps.manager.Macro.MacroPart;

/**
 *
 * @author Asus
 */
final class MacroCompiler
{
    private final HashMap<String, String> macros = new HashMap<>();
    
    MacroCompiler(Map<String, Macro> macros)
    {
        LinkedList<String> recursive = new LinkedList<>();
        macros.values().forEach((macro) -> compileMacro(macros, recursive, macro));
    }
    
    private void compileMacro(Map<String, Macro> source, LinkedList<String> recursive, Macro macro)
    {
        if(macros.containsKey(macro.getName()))
            return;
        recursive.addLast(macro.getName());
        StringBuilder sb = new StringBuilder();
        for(MacroPart part : macro.parts())
        {
            if(part.isString())
                sb.append(part.getString());
            else
            {
                Macro ref = source.get(part.getString());
                if(ref == null)
                    throw new IllegalStateException("Macro \"" + part.getString() + "\" not found");
                if(recursive.contains(ref.getName()))
                    throw new IllegalStateException("Invalid recursive marco reference: " + ref.getName());
                compileMacro(source, recursive, ref);
                sb.append(macros.get(ref.getName()));
            }
        }
        recursive.removeLast();
        macros.put(macro.getName(), sb.toString());
    }
    
    public final String compileCode(String code)
    {
        StringBuilder sb = null;
        char[] chars = code.toCharArray();
        int start = 0;
        int current;
        for(current = 0; current < chars.length; current++)
        {
            if(chars[current] == '$')
            {
                if(current + 1 >= chars.length)
                    continue;
                if(sb == null)
                    sb = new StringBuilder();
                if(current > start)
                {
                    sb.append(chars, start, current - start);
                    start = current;
                }
                
                String macro;
                if(chars[current + 1] == '{')
                {
                    start = (current++) + 1;
                    loop:
                    while(current + 1 < chars.length)
                    {
                        char c = chars[++current];
                        if(c == '}')
                            break;
                    }
                    macro = new String(chars, start, current - start);
                    current++;
                }
                else
                {
                    start++;
                    loop:
                    while(current + 1 < chars.length)
                    {
                        char c = chars[++current];
                        switch(c)
                        {
                            case ' ': case '\t': case '\r': case '\n':
                                break loop;
                        }
                    }
                    macro = new String(chars, start, current - start);
                }
                macro = macros.getOrDefault(macro, "");
                sb.append(macro);
                start = current;
            }
        }
        if(sb != null && current > start)
            sb.append(chars, start, current - start);
        return sb == null ? code : sb.toString();
    }
}
