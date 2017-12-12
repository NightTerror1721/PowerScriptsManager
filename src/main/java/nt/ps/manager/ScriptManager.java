/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nt.ps.manager;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import nt.ps.PSScript;
import nt.ps.PSState;
import nt.ps.compiler.exception.PSCompilerException;
import nt.ps.lang.ObjectSpecialOpsNames;
import nt.ps.lang.PSValue;
import nt.ps.lang.PSVarargs;

/**
 *
 * @author Asus
 */
public final class ScriptManager
{
    private final HashMap<String, CompiledScript> scripts;
    private final HashMap<Integer, CompiledSnippet> snippets;
    
    ScriptManager(PSState state, Map<String, SourceScript> scripts, Set<SourceSnippet> snippets,
            Map<String, Macro> macros, Map<String, PSValue> globalsWrapped) throws IOException, PSCompilerException
    {
        this.scripts = new HashMap<>();
        this.snippets = new HashMap<>();
        
        String source = linkSource(scripts, snippets, macros);
        compile(state, source, scripts, snippets, globalsWrapped);
    }
    
    private void compile(PSState state, String source,
            Map<String, SourceScript> sscripts, Set<SourceSnippet> ssnippets, Map<String, PSValue> globalsWrapped) throws IOException, PSCompilerException
    {
        PSScript script;
        try(ByteArrayInputStream bais = new ByteArrayInputStream(source.getBytes()))
        {
            script = state.compile(bais, source, globalsWrapped);
        }
        PSVarargs args = script.call();
        
        PSValue csnippets = args.arg(0);
        PSValue cscripts = args.arg(1);
    }
    
    static final String linkSource(Map<String, SourceScript> scripts, Set<SourceSnippet> snippets, Map<String, Macro> smacros)
    {
        MacroCompiler macros = new MacroCompiler(smacros);
        StringBuilder sb = new StringBuilder();
        
        /* link snippets */
        sb.append("const var __snippets__ = const { ");
        int count = 0;
        for(SourceSnippet s : snippets)
        {
            String code = macros.compileCode(s.getCode()).replace("\n", "\n\t\t\t");
            sb.append("\t\tcase ").append(count).append(":\n\t\t\t").append(code).append("\n\t\tbreak;\n");
        }
        sb.append("\n\t}\n};\n\n");
        
        /* link scripts */
        sb.append("const var Scripts = {};\n");
        for(SourceScript s : scripts.values())
        {
            String code = macros.compileCode(s.getCode()).replace("\n", "\n\t\t\t");
            sb.append("if(true) {\n\tconst var Script = const {\n");
            s.forEachConstant((name, c) -> {
                sb.append("\t\t").append(name).append(": ").append(c).append(",\n");
            });
            sb.append("\t\t").append(ObjectSpecialOpsNames.OPERATOR_CALL).append(": function(");
            if(s.getConstantCount() > 0)
            {
                int len = s.getParameterCount();
                for(int i=0;i<len;i++)
                {
                    sb.append(s.getParameter(i));
                    if(i < len - 1)
                        sb.append(", ");
                }
            }
            sb.append(") {\n").append(code).append("\n\t\t}\n\t};\n");
            sb.append("\tScripts.").append(s.getName()).append(" = Script;\n}\n");
        }
        sb.append("\nObject.freeze(Scripts);\n\n");
        
        /* return all */
        sb.append("return __snippets__, Scripts");
        
        return sb.toString();
    }
}
