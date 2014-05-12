/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.shelloid.script;

import java.io.Serializable;

/**
 *
 * @author Jayaraj Poroor
 */
class CompiledExpr extends CompiledObject{
    enum ExprKind {OP_EXPR, OBJ_EXPR_SEQ, LITERAL_EXPR, SCRIPT_EXPR, ASYNC_INDEX_EXPR};
    ExprKind kind;    
    CompiledExpr lexpr;
    CompiledExpr rexpr;
    String  op;
    Object  value;
    
    public CompiledExpr(SourceCtx srcCtx)
    {
        super(srcCtx);
    }
}
