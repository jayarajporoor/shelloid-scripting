/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.shelloid.script.test;

import com.shelloid.script.CompiledScript;
import com.shelloid.script.Compiler;
import com.shelloid.script.CompilerException;
import com.shelloid.script.Env;
import com.shelloid.script.Interpreter;
import com.shelloid.script.ScriptBin;
import com.shelloid.script.ShelloidObject;
import com.shelloid.script.StringSource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Jayaraj Poroor
 */
public class ExecTest {
    
    public ExecTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    @Test
    public void testRunInc() throws Exception
    {
        HashMap<String, Object> globals = new HashMap<String, Object>();
        //globals.put("count", new Long(100));
        Compiler compiler = new Compiler();
        String ssrc = "var count = 100; count = count + 1;";
        StringSource src = new StringSource("test", ssrc);
        ScriptBin bin = compiler.compile(src, globals);
        Interpreter interp = Interpreter.getInstance();
        Env env = interp.execute(bin, globals);
        assert(((Long)env.getVar("count")).intValue() == 101);
    }

    @Test
    public void testRunNoArgsMethod() throws Exception
    {
        HashMap<String, Object> globals = new HashMap<String, Object>();
        final StringBuffer methodName = new StringBuffer();
        globals.put("page", new ShelloidObject()
        {

            @Override
            public Object getField(String id) {
                return null;
            }

            @Override
            public Object invokeMethod(String name, ArrayList<Object> params, ScriptBin bin, Env env) {
                methodName.append(name);
                return null;
            }
            
        });
        Compiler compiler = new Compiler();
        String ssrc = "page.show();";
        StringSource src = new StringSource("test", ssrc);
        ScriptBin bin = compiler.compile(src, globals);
        Interpreter interp = Interpreter.getInstance();
        Env env = interp.execute(bin, globals);
        assert(methodName.toString().equals("show"));
    }

    @Test
    public void testRunScriptMethod() throws Exception
    {
        HashMap<String, Object> globals = new HashMap<String, Object>();
        globals.put("stuff", new ShelloidObject()
        {

            @Override
            public Object getField(String id) {
                return null;
            }

            @Override
            public Object invokeMethod(String name, ArrayList<Object> params, ScriptBin bin, Env env) throws Exception{
                CompiledScript script = (CompiledScript) params.get(0);
                Env newEnv = new Env(env);
                Interpreter.getInstance().executeScript(script, bin, newEnv);
                return null;
            }
            
        });
        Compiler compiler = new Compiler();
        String ssrc = "var count = 100; stuff.exec({count = count + 1;});";
        StringSource src = new StringSource("test", ssrc);
        ScriptBin bin = compiler.compile(src, globals);
        Interpreter interp = Interpreter.getInstance();
        Env env = interp.execute(bin, globals);
        assert(((Long)env.getVar("count")).intValue() == 101);
    }

    @Test
    public void testRunAsyncScriptMethod() throws Exception
    {
        final StringBuffer buf = new StringBuffer();
        final HashMap<String, Object> globals = new HashMap<String, Object>();
        globals.put("store", new ShelloidObject()
        {

            @Override
            public Object getField(String id) {
                return null;
            }

            @Override
            public Object invokeMethod(String name, ArrayList<Object> params, ScriptBin bin, Env env) throws Exception{
                buf.append(params.get(0));
                return null;
            }            
        });        
        globals.put("stuff", new ShelloidObject()
        {

            @Override
            public Object getField(String id) {
                return null;
            }

            @Override
            public Object invokeMethod(String name, ArrayList<Object> params, ScriptBin bin, Env env) throws Exception{                
                CompiledScript script = (CompiledScript) params.get(0);
                if(!script.isAsync())
                    throw new Exception("Not an async block");
                Env globalsEnv = new Env(globals);
                Env newEnv = new Env(globalsEnv);
                Interpreter.getInstance().executeScript(script, bin, newEnv);
                return null;
            }
            
        });
        Compiler compiler = new Compiler();
        String ssrc = "var count = 100; stuff.exec(async {var c = 100; c=c+1;store.set(c);});";
        StringSource src = new StringSource("test", ssrc);
        try
        {            
            ScriptBin bin = compiler.compile(src, globals);
            Interpreter interp = Interpreter.getInstance();
            Env env = interp.execute(bin, globals);
            assert(buf.toString().equals("101"));            
        }catch(CompilerException e)
        {
           Iterator<String> it = e.getErrorMsgs().iterator();
           while(it.hasNext())
           {
               System.out.println(it.next());
           }
           throw e;
        }
    }
    
}
