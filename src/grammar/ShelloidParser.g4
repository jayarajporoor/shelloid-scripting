/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
parser grammar ShelloidParser;

options {tokenVocab = ShelloidLexer;}

script: (stmt SEMI)*;

stmt: declStmt | assignStmt | exprStmt | returnStmt;

declStmt: VAR ID (ASSIGN expr)?;
assignStmt: ID ASSIGN expr;
exprStmt: expr;
returnStmt: RETURN expr;

expr: objExprSeq | LP expr RP | literal | 
      op=NOT expr | op=MINUS expr |      
      expr op=MUL expr | expr op=DIV expr | expr op=MOD expr | 
      expr op=PLUS expr | expr op=MINUS expr | expr op=EQUALS expr |      
      expr op=LT expr | expr op=GT expr | expr op=LTE expr |      
      expr op=GTE expr | expr op=NEQ expr |
      expr op=AND expr | expr op=OR expr | ASYNC? LC script RC
      ;
objExpr: ID methodCall?;
objExprSeq: objExpr (DOT objExpr)*;

methodCall: LP paramList? RP;
paramList: expr (COMMA expr)*;

literal: INT | FLOAT | STRING | TRUE | FALSE;

      
      


