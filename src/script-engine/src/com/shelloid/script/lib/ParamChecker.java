/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.shelloid.script.lib;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Jayaraj Poroor
 */
public class ParamChecker {
    public static void checkParams(String op, ArrayList<Object> params, Class[] paramTypes) 
                throws IllegalArgumentException
    {
        if(paramTypes.length != params.size())
        {
            throw new IllegalArgumentException("Wrong number of arguments passed to " 
                        + op + ". Required:  " + paramTypes.length + ", Found: " + params.size());
        }
        Iterator<Object> it = params.iterator();
        int i = 0;
        while(it.hasNext())
        {
            Object obj = it.next();
            if(!paramTypes[i].isInstance(obj))
            {
                throw new IllegalArgumentException("Wrong argument type passed to " 
                        + op + ". Argument " + i + " required type:  " + paramTypes[i].getName() +
                        " found: " + obj.getClass().getName()
                        );
                
            }
            i++;
        }
    }
}
