/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
lexer grammar ShelloidLexer;

LC : '{';
RC : '}';
LP: '(';
RP: ')';
VAR: 'var';
ASSIGN: '=';
INT: DIGIT+;
STRING: '"' (ESC|.)*? '"';
FLOAT: DIGIT+ '.' DIGIT+ | '.' DIGIT+;
TRUE: 'true';
FALSE: 'false';
DIV: '/';
MUL : '*';
MOD: '%';
PLUS: '+';
MINUS: '-';
EQUALS: '==';
AND: '&&';
OR: '||';
NOT: '!';
LT : '<';
GT : '>';
GTE : '>=';
LTE : '<=';
NEQ  : '!=';
DOT : '.';
RETURN: 'return';
ASYNC: 'async';
ID:  (LETTER) (LETTER | DIGIT | '-' | '?' | '!')*;
WS : [ \r\n\t]+ -> skip;
COMMENT : (('/*' .*? '*/') | ('//' .*? '\n')) -> skip;
COMMA: ',';
SEMI: ';';

fragment
ESC : '\\"' | '\\\\';
LETTER : [a-zA-Z];
DIGIT  : [0-9];




       
