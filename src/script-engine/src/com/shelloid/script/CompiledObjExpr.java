/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.shelloid.script;

import java.util.ArrayList;

/**
 *
 * @author Jayaraj Poroor
 */
class CompiledObjExpr extends CompiledObject {
    enum ObjExprKind {METHOD_CALL, OBJ_REF};
    
    ObjExprKind kind;        
    String id;
    ArrayList<CompiledExpr> params;
    public CompiledObjExpr(SourceCtx srcCtx)
    {
        super(srcCtx);
    }
}
