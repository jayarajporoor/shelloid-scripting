/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.shelloid.script.test;

import com.shelloid.script.Compiler;
import com.shelloid.script.StringSource;
import java.util.HashMap;
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
    public void testCompile() throws Exception
    {
        HashMap<String, Object> globals = new HashMap<String, Object>();
        globals.put("count", new Long(100));
        Compiler compiler = new Compiler();
        String ssrc = "count = count + 1;";
        StringSource src = new StringSource("test", ssrc);
        compiler.compile(src, globals);
        assert(((Long)globals.get("count")).intValue() == 101);
    }
}
