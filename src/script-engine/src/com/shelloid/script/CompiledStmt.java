/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.shelloid.script;

/**
 *
 * @author Jayaraj Poroor
 */
class CompiledStmt extends CompiledObject {

    enum StmtKind {ASSIGN_STMT, DECL_STMT, EXPR_STMT, RET_STMT};
    StmtKind kind;
    String   id;
    CompiledExpr expr; 
    
    public CompiledStmt(SourceCtx srcCtx)
    {
        super(srcCtx);
    }
}
